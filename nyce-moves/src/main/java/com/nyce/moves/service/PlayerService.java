package com.nyce.moves.service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.model.ChangeEmailRequest;
import com.nyce.moves.model.ChangePasswordRequest;
import com.nyce.moves.model.Player;
import com.nyce.moves.model.PlayerRequest;
import com.nyce.moves.model.PlayerResponse;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.model.ResponseTemplate.StatusEnum;
import com.nyce.moves.model.UpdatePlayerRequest;
import com.nyce.moves.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	private AmazonClient amazonClient;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public PlayerResponse addPlayer(PlayerRequest playerRequest, PlayerResponse playerResponse) {

		if (playerRequest.getEmail() != null) {

			List<Player> players = playerRepository.findByEmail(playerRequest.getEmail());

			if (players != null && players.size() > 0) {
				playerResponse.setStatus(PlayerResponse.StatusEnum.FAILURE);
				playerResponse.setMessage("Player with the same email address [" + playerRequest.getEmail() + "] exists in the system, cannot create player. EmailId is the primary key");
				playerResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			} else {
				Player playerObject = new Player();
				playerObject.setEmail(playerRequest.getEmail());
				playerObject.setFirstName(playerRequest.getFirstName());
				playerObject.setLastName(playerRequest.getLastName());
				playerObject.setPassword(bCryptPasswordEncoder.encode(playerRequest.getPassword()));
				playerObject.setDisplayName(playerRequest.getDisplayName());
				playerObject.setCity(playerRequest.getCity());
				playerObject.setCountry(playerRequest.getCountry());
				playerObject.setSchool(playerRequest.getSchool());
				playerObject.setPrimarySport(playerRequest.getPrimarySport());
				playerObject.setDob(playerRequest.getDob());
				playerObject.setNumberOfConnections(0L);
				playerObject.setCreationTime(OffsetDateTime.now());
				playerObject.setProfileImageUrl(playerRequest.getProfileImageUrl());
				if (playerRequest.getGender() != null && playerRequest.getGender().toString().equalsIgnoreCase(Player.GenderEnum.MALE.toString())) {
					playerObject.setGender(Player.GenderEnum.MALE);
				} else if (playerRequest.getGender() != null && playerRequest.getGender().toString().equalsIgnoreCase(Player.GenderEnum.FEMALE.toString())) {
					playerObject.setGender(Player.GenderEnum.FEMALE);
				}

				Player player = playerRepository.save(playerObject);
				if (player != null) {
					player = populatePreSignedUrl(player);
					playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
					playerResponse.setMessage("Player has been successfully created");
					playerResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					playerResponse.setData(player);
				} else {
					playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
					playerResponse.setMessage("Player has not beein created in the system");
					playerResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
				}
			}
		} else {
			playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
			playerResponse.setMessage("Player has not beein created in the system");
			playerResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
		}

		return playerResponse;
	}

	public PlayerResponse updatePlayer(UpdatePlayerRequest player, Long playerId, PlayerResponse playerResponse) {

		Player playerInSystem = playerRepository.findOne(playerId);

		if (player.getFirstName() != null) {
			playerInSystem.setFirstName(player.getFirstName());
		}
		if (player.getLastName() != null) {
			playerInSystem.setLastName(player.getLastName());
		}
		if (player.getDisplayName() != null) {
			playerInSystem.setDisplayName(player.getDisplayName());
		}
		if (player.getCity() != null) {
			playerInSystem.setCity(player.getCity());
		}
		if (player.getCountry() != null) {
			playerInSystem.setCountry(player.getCountry());
		}
		if (player.getGender() != null && player.getGender().toString().equalsIgnoreCase(Player.GenderEnum.MALE.toString())) {
			playerInSystem.setGender(Player.GenderEnum.MALE);
		} else if (player.getGender() != null && player.getGender().toString().equalsIgnoreCase(Player.GenderEnum.FEMALE.toString())) {
			playerInSystem.setGender(Player.GenderEnum.FEMALE);
		}
		if (player.getSchool() != null) {
			playerInSystem.setSchool(player.getSchool());
		}
		if (player.getPrimarySport() != null) {
			playerInSystem.setPrimarySport(player.getPrimarySport());
		}
		if (player.getDob() != null) {
			playerInSystem.setDob(player.getDob());
		}
		if (player.getProfileImageUrl() != null) {
			playerInSystem.setProfileImageUrl(player.getProfileImageUrl());
		}
		playerInSystem.setUpdateTime(OffsetDateTime.now());

		Player updatedPlayer = playerRepository.save(playerInSystem);
		if (updatedPlayer != null) {
			updatedPlayer = populatePreSignedUrl(updatedPlayer);
			playerResponse.setData(updatedPlayer);
			playerResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			playerResponse.setMessage("Player has been updated successfully");
			playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
		} else {
			playerResponse.setCode(ApplicationConstants.FAILURE_CODE_31002);
			playerResponse.setMessage("Player with playerId [" + playerId + "] was not updated in the system");
			playerResponse.setStatus(PlayerResponse.StatusEnum.FAILURE);
		}

		return playerResponse;

	}

	public PlayerResponse loginPlayer(String username, String password, PlayerResponse playerResponse) {

		List<Player> players = playerRepository.findByEmail(username);

		if (players != null && players.size() > 0) {
			String passwordInSystem = players.get(0).getPassword();
			if (passwordInSystem != null && passwordInSystem.length() > 0) {
				if (bCryptPasswordEncoder.matches(password, passwordInSystem)) {
					playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
					playerResponse.setMessage("Successful Login");
					playerResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					playerResponse.setData(populatePreSignedUrl(players.get(0)));
					return playerResponse;
				}
			}
		}

		playerResponse.setStatus(PlayerResponse.StatusEnum.FAILURE);
		playerResponse.setMessage("Unsuccessful Login");
		playerResponse.setCode(ApplicationConstants.FAILURE_CODE_31002);
		return playerResponse;

	}

	public ResponseTemplate changePassword(Long playerId, ChangePasswordRequest changePasswordRequest, ResponseTemplate responseTemplate) {

		Player player = playerRepository.findOne(playerId);

		if (player != null) {
			String passwordInSystem = player.getPassword();
			if (passwordInSystem != null && passwordInSystem.length() > 0) {
				if (bCryptPasswordEncoder.matches(changePasswordRequest.getCurrentPassword(), passwordInSystem)) {
					player.setPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
					playerRepository.save(player);

					responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					responseTemplate.setMessage("Passowrd has been changed successfully for user with playerId [" + playerId + "]");
					responseTemplate.setStatus(StatusEnum.SUCCESS);
					return responseTemplate;
				}
			}
		}

		responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
		responseTemplate.setMessage("Passowrd has not been changed for user with playerId [" + playerId + "], please check the credentials");
		responseTemplate.setStatus(StatusEnum.FAILURE);
		return responseTemplate;

	}

	public PlayerResponse getPlayer(Long playerId, PlayerResponse playerResponse) {
		Player player = playerRepository.findOne(playerId);

		if (player != null) {
			player = populatePreSignedUrl(player);
			playerResponse.setData(player);
			playerResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			playerResponse.setMessage("Player has been fetched successfully");
			playerResponse.setStatus(PlayerResponse.StatusEnum.SUCCESS);
		} else {
			playerResponse.setData(player);
			playerResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			playerResponse.setMessage("Player with playerId [" + playerId + "] doesn't exists in the system");
			playerResponse.setStatus(PlayerResponse.StatusEnum.FAILURE);
		}

		return playerResponse;
	}

	public ResponseTemplate deletePlayer(Long playerId, ResponseTemplate responseTemplate) {
		Player player = playerRepository.findOne(playerId);

		if (player != null) {
			playerRepository.delete(playerId);
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
			responseTemplate.setMessage("Player with playerId [" + playerId + "] has been deleted successfully");
			responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
		} else {
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
			responseTemplate.setMessage("Player with playerId [" + playerId + "] does not exist in the system");
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
		}

		return responseTemplate;

	}

	public ResponseTemplate resetPassword(String emailAddress, ResponseTemplate responseTemplate) {

		List<Player> players = playerRepository.findByEmail(emailAddress);

		if (players != null && players.size() > 0) {
			try {

				String newPassword = "newPassword"; // getAlphaNumericString(10);
				players.get(0).setPassword(bCryptPasswordEncoder.encode(newPassword));
				playerRepository.save(players.get(0));
				// sendmail(newPassword);
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
				responseTemplate.setMessage("New Password has been successfully sent to the registered email address [" + emailAddress + "]");
				responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			} catch (Exception e) {
				e.printStackTrace();
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
				responseTemplate.setMessage("Not able to send the mail to the registered email address [" + emailAddress + "]");
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			}
		} else {
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
			responseTemplate.setMessage("No registered email address against email address [" + emailAddress + "]");
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
		}

		return responseTemplate;

	}

	public Player populatePreSignedUrl(Player player) {

		if (player.getProfileImageUrl() != null && player.getProfileImageUrl() != "") {
			String objectName = amazonClient.getObjectNameFromS3Url(player.getProfileImageUrl());
			String presignedUrl = amazonClient.generatePreSignedUrl(objectName);
			player.setProfileImagePreSignedUrl(presignedUrl);
		}

		return player;

	}

	private void sendmail(String newPassword) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("XXXXX@gmail.com", "XXXXXXXXX@19");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("nycemoves@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("XXXXXXXXXXXXXXX@gmail.com"));
		msg.setSubject("NyceMoves :: Password Reset Request");
		msg.setContent("Your new password is " + newPassword, "text/html");
		msg.setSentDate(new Date());

		/*
		 * MimeBodyPart messageBodyPart = new MimeBodyPart();
		 * messageBodyPart.setContent("Tutorials point email", "text/html");
		 * 
		 * Multipart multipart = new MimeMultipart();
		 * multipart.addBodyPart(messageBodyPart); MimeBodyPart attachPart = new
		 * MimeBodyPart();
		 * 
		 * attachPart.attachFile("/var/tmp/image19.png");
		 * multipart.addBodyPart(attachPart); msg.setContent(multipart);
		 */
		Transport.send(msg);
	}

	static String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz" + "@#$!";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public ResponseTemplate changePrimaryEmail(Long playerId, ChangeEmailRequest changeEmailRequest, ResponseTemplate responseTemplate) {

		Player player = playerRepository.findOne(playerId);

		if (player != null) {

			if (player.getEmail().equalsIgnoreCase(changeEmailRequest.getCurrentEmail())) {
				player.setEmail(changeEmailRequest.getNewEmail());
				playerRepository.save(player);
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.SUCCESS);
				responseTemplate.setMessage("Player with playerId [" + playerId + "] has been successfully updated with new email address [" + changeEmailRequest.getNewEmail() + "]");
				responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			} else {
				responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
				responseTemplate.setMessage("Player with playerId [" + playerId + "] does not exist in the system / Email does not matched with the current email");
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			}

		} else {
			responseTemplate.setStatus(ResponseTemplate.StatusEnum.FAILURE);
			responseTemplate.setMessage("Player with playerId [" + playerId + "] does not exist in the system");
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
		}

		return responseTemplate;

	}

}
