package com.nyce.moves.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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
import com.nyce.moves.common.UtilityFunctions;
import com.nyce.moves.model.ChangeEmailRequest;
import com.nyce.moves.model.ChangePasswordRequest;
import com.nyce.moves.model.DashboardElement;
import com.nyce.moves.model.DashboardElement.DashboardElementTypeEnum;
import com.nyce.moves.model.Friend;
import com.nyce.moves.model.GetDashBoardResponse;
import com.nyce.moves.model.GetFriendsResponse;
import com.nyce.moves.model.GetPendingFriendsRequestsResponse;
import com.nyce.moves.model.Image;
import com.nyce.moves.model.Player;
import com.nyce.moves.model.PlayerRequest;
import com.nyce.moves.model.PlayerResponse;
import com.nyce.moves.model.Post;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.model.ResponseTemplate.StatusEnum;
import com.nyce.moves.model.UpdatePlayerRequest;
import com.nyce.moves.model.Video;
import com.nyce.moves.repository.ImageRepository;
import com.nyce.moves.repository.PlayerRepository;
import com.nyce.moves.repository.PostRepository;
import com.nyce.moves.repository.VideoRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	ImageRepository imageRepository;

	@Autowired
	VideoRepository videoRepository;

	@Autowired
	PostRepository postRepository;

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
				if (playerObject.getDisplayName() == null) {
					String displayName = "";
					if (playerObject.getFirstName() != null && playerObject.getLastName() != null) {
						displayName = playerObject.getFirstName() + " " + playerObject.getLastName();
					} else if (playerObject.getFirstName() != null) {
						displayName = playerObject.getFirstName();
					} else if (playerObject.getLastName() != null) {
						displayName = playerObject.getLastName();
					}
					playerObject.setDisplayName(displayName);
				}
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

					if (player.getProfileImageUrl() != null && player.getProfileImageUrl() != "") {
						String profileUrl = player.getProfileImageUrl();
						String oldFileName = profileUrl.substring(profileUrl.lastIndexOf("/") + 1);
						if (oldFileName.charAt(0) == '0') {
							String sourceKey = amazonClient.getFileKeyFromUrl(playerRequest.getProfileImageUrl());
							String destinationKey = (sourceKey.replace("0/", "" + player.getPlayerId() + '/')).replace("0_", "" + player.getPlayerId() + "_");
							// 0/videos/0_video_1554793308240-abc.mp4
							amazonClient.copyObjectInS3(amazonClient.getFileKeyFromUrl(playerRequest.getProfileImageUrl()), destinationKey);
							amazonClient.deleteFileFromS3Bucket(player.getProfileImageUrl());
							player.setProfileImageUrl((player.getProfileImageUrl().replace("0/", "" + player.getPlayerId() + '/')).replace("0_", "" + player.getPlayerId() + "_"));
						}

					}
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
			if (player.getFriends() != null && player.getFriends().size() > 0) {
				player.setNumberOfConnections(new Long(player.getFriends().size()));
			}
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

	public GetDashBoardResponse getTimeline(Long playerId, BigDecimal pageSize, BigDecimal pageNumber, GetDashBoardResponse getDashBoardResponse) {

		List<DashboardElement> dashboardElements = new ArrayList<DashboardElement>();
		Player player = playerRepository.findOne(playerId);

		List<Image> images = imageRepository.findByPlayerId(playerId);
		if (images != null && images.size() > 0) {
			for (Image image : images) {
				DashboardElement dashboardElement = new DashboardElement();
				dashboardElement.setDashboardElementId(image.getImageId());
				dashboardElement.setDashboardElementType(DashboardElementTypeEnum.IMAGE);
				dashboardElement.setDescription(image.getDescription());
				dashboardElement.setApplauds(image.getApplauds());
				if (image.getApplaudDoneByPlayerIds() != null && image.getApplaudDoneByPlayerIds().size() > 0) {
					if (image.getApplaudDoneByPlayerIds().contains(playerId)) {
						dashboardElement.setApplaudDoneBySignedInPlayer(true);
					}
				}
				dashboardElement.setPlayerId(image.getPlayerId());
				dashboardElement.setPostedTimestamp(image.getPostedTimestamp());
				dashboardElement.setUrl(image.getImageUrl());
				dashboardElement.setTitle(image.getTitle());
				if (image.getHeight() != null) {
					dashboardElement.setImageHeight(new Long(image.getHeight()));
				}
				if (image.getWidth() != null) {
					dashboardElement.setImageWidth(new Long(image.getWidth()));
				}
				if (image.getComments() != null && image.getComments().size() > 0) {
					dashboardElement.setNumberOfComments(new Long(image.getComments().size()));
				} else {
					dashboardElement.setNumberOfComments(0L);
				}
				dashboardElements.add(dashboardElement);

			}
		}

		List<Video> videos = videoRepository.findByPlayerId(playerId);
		if (videos != null && videos.size() > 0) {
			for (Video video : videos) {
				DashboardElement dashboardElement = new DashboardElement();
				dashboardElement.setDashboardElementId(video.getVideoId());
				dashboardElement.setDashboardElementType(DashboardElementTypeEnum.VIDEO);
				dashboardElement.setDescription(video.getDescription());
				dashboardElement.setApplauds(video.getApplauds());
				if (video.getApplaudDoneByPlayerIds() != null && video.getApplaudDoneByPlayerIds().size() > 0) {
					if (video.getApplaudDoneByPlayerIds().contains(playerId)) {
						dashboardElement.setApplaudDoneBySignedInPlayer(true);
					}
				}
				dashboardElement.setPlayerId(video.getPlayerId());
				dashboardElement.setPostedTimestamp(video.getPostedTimestamp());
				dashboardElement.setUrl(video.getVideoUrl());
				dashboardElement.setTitle(video.getTitle());
				if (video.getComments() != null && video.getComments().size() > 0) {
					dashboardElement.setNumberOfComments(new Long(video.getComments().size()));
				} else {
					dashboardElement.setNumberOfComments(0L);
				}
				dashboardElements.add(dashboardElement);

			}
		}

		List<Post> posts = postRepository.findByPostedBy(playerId);
		if (posts != null && posts.size() > 0) {
			for (Post post : posts) {
				DashboardElement dashboardElement = new DashboardElement();
				dashboardElement.setDashboardElementId(post.getPostId());
				dashboardElement.setDashboardElementType(DashboardElementTypeEnum.POST);
				dashboardElement.setDescription(post.getPost());
				dashboardElement.setApplauds(post.getApplauds());
				if (post.getApplaudDoneByPlayerIds() != null && post.getApplaudDoneByPlayerIds().size() > 0) {
					if (post.getApplaudDoneByPlayerIds().contains(playerId)) {
						dashboardElement.setApplaudDoneBySignedInPlayer(true);
					}
				}
				dashboardElement.setPlayerId(post.getPostedBy());
				dashboardElement.setPostedTimestamp(post.getPostedTimestamp());
				if (post.getComments() != null && post.getComments().size() > 0) {
					dashboardElement.setNumberOfComments(new Long(post.getComments().size()));
				} else {
					dashboardElement.setNumberOfComments(0L);
				}
				dashboardElements.add(dashboardElement);
			}
		}

		if (dashboardElements != null && dashboardElements.size() > 0) {
			// Sort the list based on posted time stamp
			dashboardElements.sort((DashboardElement i1, DashboardElement i2) -> i2.getPostedTimestamp().compareTo(i1.getPostedTimestamp()));
			UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), dashboardElements);

			getDashBoardResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getDashBoardResponse.setMessage(paginationReturn.getReturnMessage());
			getDashBoardResponse.setStatus(GetDashBoardResponse.StatusEnum.SUCCESS);
			getDashBoardResponse.setPageNumber(pageNumber.longValue());
			getDashBoardResponse.setPageSize(pageSize.longValue());
			getDashBoardResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
			List<DashboardElement> returnDashboardElements = (List<DashboardElement>) paginationReturn.getReturnList();

			for (DashboardElement dashboardElement : returnDashboardElements) {
				Long elementPlayerId = dashboardElement.getPlayerId();
				Player elementPlayer = playerRepository.findOne(elementPlayerId);
				if (elementPlayer != null) {
					dashboardElement.setDisplayName(elementPlayer.getDisplayName());
					dashboardElement.setProfileImageUrl(player.getProfileImageUrl());
					if (dashboardElement.getProfileImageUrl() != null) {
						dashboardElement.setProfilePreSignUrl(amazonClient.getPreSignUrlFromUrl(player.getProfileImageUrl()));
					}
				}
				if (dashboardElement.getUrl() != null) {
					dashboardElement.setPreSignedUrl(amazonClient.getPreSignUrlFromUrl(dashboardElement.getUrl()));
				}
			}
			getDashBoardResponse.setData(modifyPreSignedUrls(returnDashboardElements));
		} else {
			getDashBoardResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getDashBoardResponse.setMessage("No records are present for the playerId [" + playerId + "]");
			getDashBoardResponse.setStatus(GetDashBoardResponse.StatusEnum.FAILURE);
			getDashBoardResponse.setPageNumber(pageNumber.longValue());
			getDashBoardResponse.setPageSize(pageSize.longValue());
			getDashBoardResponse.setTotalNumberofPagesAvailable(0l);
		}

		return getDashBoardResponse;
	}

	public List<DashboardElement> modifyPreSignedUrls(List<DashboardElement> dashboardElements) {

		for (DashboardElement dashboardElement : dashboardElements) {
			if (dashboardElement.getUrl() != null && dashboardElement.getUrl() != "") {
				String objectName = amazonClient.getObjectNameFromS3Url(dashboardElement.getUrl());
				String presignedUrl = amazonClient.generatePreSignedUrl(objectName);
				dashboardElement.setPreSignedUrl(presignedUrl);
			}
		}

		return dashboardElements;
	}

	public ResponseTemplate sendFriendRequest(Long playerId, Long friendId, ResponseTemplate responseTemplate) {

		Player player = playerRepository.findOne(playerId);

		if (player != null) {
			Player friend = playerRepository.findOne(friendId);

			if (friend != null) {

				boolean isFriendRequestAlreadyExists = false;
				// Get the pending friend request for the users
				List<Friend> pendingFriendRequestList = friend.getPendingFriendRequests();
				if (pendingFriendRequestList != null && pendingFriendRequestList.size() > 0) {
					for (Friend pendingFriend : pendingFriendRequestList) {
						if (pendingFriend.getPlayerId().longValue() == playerId) {
							isFriendRequestAlreadyExists = true;
							break;
						}
					}
				}

				if (!isFriendRequestAlreadyExists) {
					// Get the pending friend request for the users
					pendingFriendRequestList = player.getPendingFriendRequests();
					if (pendingFriendRequestList != null && pendingFriendRequestList.size() > 0) {
						for (Friend pendingFriend : pendingFriendRequestList) {
							if (pendingFriend.getPlayerId().longValue() == friendId) {
								isFriendRequestAlreadyExists = true;
								break;
							}
						}
					}
				}

				if (!isFriendRequestAlreadyExists) {
					Friend friendObject = new Friend();
					friendObject.setPlayerId(playerId);
					friendObject.setName(player.getDisplayName());
					friendObject.setDisplayImageUrl(player.getProfileImageUrl());
					friend.addPendingFriendRequestsItem(friendObject);

					playerRepository.save(friend);

					responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					responseTemplate.setMessage("Friend Request has been successfully placed to player with id [" + friendId + "] by player with id [" + playerId + "]");
					responseTemplate.setStatus(StatusEnum.SUCCESS);
					return responseTemplate;

				} else {
					responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
					responseTemplate.setMessage("Friend Request Already Exists between [" + friendId + "] and [" + playerId + "]");
					responseTemplate.setStatus(StatusEnum.FAILURE);
					return responseTemplate;
				}

			} else {
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
				responseTemplate.setMessage("Friend Request failed. No player exist with friend Id [" + friendId + "]");
				responseTemplate.setStatus(StatusEnum.FAILURE);
				return responseTemplate;
			}

		} else {
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setMessage("Friend Request failed. Incorrect requesting player, no existing in the system [" + playerId + "]");
			responseTemplate.setStatus(StatusEnum.FAILURE);
			return responseTemplate;
		}

	}

	public GetPendingFriendsRequestsResponse getPendingFriendsRequest(Long playerId, GetPendingFriendsRequestsResponse getPendingFriendsRequestsResponse) {

		Player player = playerRepository.findOne(playerId);

		if (player != null) {
			getPendingFriendsRequestsResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getPendingFriendsRequestsResponse.setStatus(GetPendingFriendsRequestsResponse.StatusEnum.SUCCESS);
			if (player.getPendingFriendRequests() != null && player.getPendingFriendRequests().size() > 0) {
				getPendingFriendsRequestsResponse.setNumberOfPendingRequests(new Long(player.getPendingFriendRequests().size()));
				getPendingFriendsRequestsResponse.setData(addPreSignedUrlsToFriends(player.getPendingFriendRequests()));
				getPendingFriendsRequestsResponse.setMessage("Successfully fetched [" + player.getPendingFriendRequests().size() + "] pending freind requests");
			} else {
				getPendingFriendsRequestsResponse.setNumberOfPendingRequests(0l);
				getPendingFriendsRequestsResponse.setMessage("Successfully fetched [" + 0 + "] pending freind requests");
			}
		} else {
			getPendingFriendsRequestsResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getPendingFriendsRequestsResponse.setStatus(GetPendingFriendsRequestsResponse.StatusEnum.FAILURE);
			getPendingFriendsRequestsResponse.setMessage("Invalid player Id specified, does not exist within the system [" + playerId + "].");
		}

		return getPendingFriendsRequestsResponse;
	}

	public ResponseTemplate approveFriendRequest(Long playerId, Long friendId, ResponseTemplate responseTemplate) {

		Player player = playerRepository.findOne(playerId);

		if (player != null) {
			Player friend = playerRepository.findOne(friendId);

			if (friend != null) {
				boolean friendRequestExists = player.getPendingFriendRequests().removeIf(f -> f.getPlayerId().longValue() == friendId);

				if (friendRequestExists) {
					Friend friendObjectA = new Friend();
					friendObjectA.setPlayerId(friendId);
					friendObjectA.setName(friend.getDisplayName());
					friendObjectA.setDisplayImageUrl(friend.getProfileImageUrl());
					player.addFriendsItem(friendObjectA);
					player.setNumberOfConnections(player.getNumberOfConnections() + 1);
					playerRepository.save(player);

					Friend friendObjectB = new Friend();
					friendObjectB.setPlayerId(playerId);
					friendObjectB.setName(player.getDisplayName());
					friendObjectB.setDisplayImageUrl(player.getProfileImageUrl());
					friend.addFriendsItem(friendObjectB);
					friend.setNumberOfConnections(friend.getNumberOfConnections() + 1);
					playerRepository.save(friend);

					responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					responseTemplate.setMessage("Friend with id [" + friendId + "] has been  successfully added in the list of friends of player with id [" + playerId + "]");
					responseTemplate.setStatus(StatusEnum.SUCCESS);
					return responseTemplate;

				} else {
					responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
					responseTemplate.setMessage("Friend Request does not exists with friendId [" + friendId + "] for player [" + playerId + "]");
					responseTemplate.setStatus(StatusEnum.FAILURE);
					return responseTemplate;
				}
			} else {
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
				responseTemplate.setMessage("Friend Approval Request failed. Incorrect friend, not existing in the system [" + friendId + "]");
				responseTemplate.setStatus(StatusEnum.FAILURE);
				return responseTemplate;
			}

		} else {
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setMessage("Friend Approval Request failed. Incorrect requesting player, no existing in the system [" + playerId + "]");
			responseTemplate.setStatus(StatusEnum.FAILURE);
			return responseTemplate;
		}

	}

	public GetFriendsResponse getFriends(Long playerId, BigDecimal pageSize, BigDecimal pageNumber, GetFriendsResponse getFriendsResponse) {

		Player player = playerRepository.findOne(playerId);

		if (player != null && player.getFriends() != null && player.getFriends().size() > 0) {

			UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), player.getFriends());
			getFriendsResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getFriendsResponse.setMessage(paginationReturn.getReturnMessage());
			getFriendsResponse.setStatus(GetFriendsResponse.StatusEnum.SUCCESS);
			getFriendsResponse.setPageNumber(pageNumber.longValue());
			getFriendsResponse.setPageSize(pageSize.longValue());
			getFriendsResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
			getFriendsResponse.setData(addPreSignedUrlsToFriends((List<Friend>) paginationReturn.getReturnList()));
		} else {
			getFriendsResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getFriendsResponse.setMessage("No records are present for the playerId [" + playerId + "]");
			getFriendsResponse.setStatus(GetFriendsResponse.StatusEnum.FAILURE);
			getFriendsResponse.setPageNumber(pageNumber.longValue());
			getFriendsResponse.setPageSize(pageSize.longValue());
			getFriendsResponse.setTotalNumberofPagesAvailable(0l);
		}

		return getFriendsResponse;
	}

	public List<Friend> addPreSignedUrlsToFriends(List<Friend> friends) {

		for (Friend friend : friends) {
			if (friend.getDisplayImageUrl() != null && friend.getDisplayImageUrl() != "") {
				String objectName = amazonClient.getObjectNameFromS3Url(friend.getDisplayImageUrl());
				String presignedUrl = amazonClient.generatePreSignedUrl(objectName);
				friend.setDisplayImagePreSignedUrl(presignedUrl);
			}
		}

		return friends;
	}

	public GetDashBoardResponse getDashboard(Long playerId, BigDecimal pageSize, BigDecimal pageNumber, GetDashBoardResponse getDashBoardResponse) {

		List<DashboardElement> dashboardElements = new ArrayList<DashboardElement>();
		Player player = playerRepository.findOne(playerId);
		List<Long> playerIdList = new ArrayList<Long>();

		if (player != null && player.getFriends() != null && player.getFriends().size() > 0) {
			for (Friend friend : player.getFriends()) {
				playerIdList.add(friend.getPlayerId());
			}
		}
		playerIdList.add(playerId);

		List<Image> images = imageRepository.fetchImagesByPlayerIdList(playerIdList);
		if (images != null && images.size() > 0) {
			for (Image image : images) {
				DashboardElement dashboardElement = new DashboardElement();
				dashboardElement.setDashboardElementId(image.getImageId());
				dashboardElement.setDashboardElementType(DashboardElementTypeEnum.IMAGE);
				dashboardElement.setDescription(image.getDescription());
				dashboardElement.setApplauds(image.getApplauds());
				if (image.getApplaudDoneByPlayerIds() != null && image.getApplaudDoneByPlayerIds().size() > 0) {
					if (image.getApplaudDoneByPlayerIds().contains(playerId)) {
						dashboardElement.setApplaudDoneBySignedInPlayer(true);
					}
				}
				dashboardElement.setPlayerId(image.getPlayerId());
				dashboardElement.setPostedTimestamp(image.getPostedTimestamp());
				dashboardElement.setUrl(image.getImageUrl());
				dashboardElement.setTitle(image.getTitle());
				if (image.getHeight() != null) {
					dashboardElement.setImageHeight(new Long(image.getHeight()));
				}
				if (image.getWidth() != null) {
					dashboardElement.setImageWidth(new Long(image.getWidth()));
				}
				if (image.getComments() != null && image.getComments().size() > 0) {
					dashboardElement.setNumberOfComments(new Long(image.getComments().size()));
				} else {
					dashboardElement.setNumberOfComments(0L);
				}
				dashboardElements.add(dashboardElement);

			}
		}

		List<Video> videos = videoRepository.fetchVideosByPlayerIdList(playerIdList);
		if (videos != null && videos.size() > 0) {
			for (Video video : videos) {
				DashboardElement dashboardElement = new DashboardElement();
				dashboardElement.setDashboardElementId(video.getVideoId());
				dashboardElement.setDashboardElementType(DashboardElementTypeEnum.VIDEO);
				dashboardElement.setDescription(video.getDescription());
				dashboardElement.setApplauds(video.getApplauds());
				if (video.getApplaudDoneByPlayerIds() != null && video.getApplaudDoneByPlayerIds().size() > 0) {
					if (video.getApplaudDoneByPlayerIds().contains(playerId)) {
						dashboardElement.setApplaudDoneBySignedInPlayer(true);
					}
				}
				dashboardElement.setPlayerId(video.getPlayerId());
				dashboardElement.setPostedTimestamp(video.getPostedTimestamp());
				dashboardElement.setUrl(video.getVideoUrl());
				dashboardElement.setTitle(video.getTitle());
				if (video.getComments() != null && video.getComments().size() > 0) {
					dashboardElement.setNumberOfComments(new Long(video.getComments().size()));
				} else {
					dashboardElement.setNumberOfComments(0L);
				}
				dashboardElements.add(dashboardElement);

			}
		}

		List<Post> posts = postRepository.fetchPostsByPlayerIdList(playerIdList);
		if (posts != null && posts.size() > 0) {
			for (Post post : posts) {
				DashboardElement dashboardElement = new DashboardElement();
				dashboardElement.setDashboardElementId(post.getPostId());
				dashboardElement.setDashboardElementType(DashboardElementTypeEnum.POST);
				dashboardElement.setDescription(post.getPost());
				dashboardElement.setApplauds(post.getApplauds());
				if (post.getApplaudDoneByPlayerIds() != null && post.getApplaudDoneByPlayerIds().size() > 0) {
					if (post.getApplaudDoneByPlayerIds().contains(playerId)) {
						dashboardElement.setApplaudDoneBySignedInPlayer(true);
					}
				}
				dashboardElement.setPlayerId(post.getPostedBy());
				dashboardElement.setPostedTimestamp(post.getPostedTimestamp());
				if (post.getComments() != null && post.getComments().size() > 0) {
					dashboardElement.setNumberOfComments(new Long(post.getComments().size()));
				} else {
					dashboardElement.setNumberOfComments(0L);
				}
				dashboardElements.add(dashboardElement);
			}
		}

		if (dashboardElements != null && dashboardElements.size() > 0) {
			// Sort the list based on posted time stamp
			dashboardElements.sort((DashboardElement i1, DashboardElement i2) -> i2.getPostedTimestamp().compareTo(i1.getPostedTimestamp()));
			UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), dashboardElements);

			getDashBoardResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getDashBoardResponse.setMessage(paginationReturn.getReturnMessage());
			getDashBoardResponse.setStatus(GetDashBoardResponse.StatusEnum.SUCCESS);
			getDashBoardResponse.setPageNumber(pageNumber.longValue());
			getDashBoardResponse.setPageSize(pageSize.longValue());
			getDashBoardResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
			List<DashboardElement> returnDashboardElements = (List<DashboardElement>) paginationReturn.getReturnList();

			for (DashboardElement dashboardElement : returnDashboardElements) {
				Long elementPlayerId = dashboardElement.getPlayerId();
				Player elementPlayer = playerRepository.findOne(elementPlayerId);
				if (elementPlayer != null) {
					dashboardElement.setDisplayName(elementPlayer.getDisplayName());
					dashboardElement.setProfileImageUrl(player.getProfileImageUrl());
					if (player.getProfileImageUrl() != null) {
						dashboardElement.setProfilePreSignUrl(amazonClient.getPreSignUrlFromUrl(player.getProfileImageUrl()));
					}
				}
				if (dashboardElement.getUrl() != null) {
					dashboardElement.setPreSignedUrl(amazonClient.getPreSignUrlFromUrl(dashboardElement.getUrl()));
				}
			}
			getDashBoardResponse.setData(modifyPreSignedUrls(returnDashboardElements));
		} else {
			getDashBoardResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getDashBoardResponse.setMessage("No records are present for the playerId [" + playerId + "]");
			getDashBoardResponse.setStatus(GetDashBoardResponse.StatusEnum.FAILURE);
			getDashBoardResponse.setPageNumber(pageNumber.longValue());
			getDashBoardResponse.setPageSize(pageSize.longValue());
			getDashBoardResponse.setTotalNumberofPagesAvailable(0l);
		}

		return getDashBoardResponse;
	}

	public GetFriendsResponse searchPlayersByString(Long playerId, String searchString, BigDecimal pageSize, BigDecimal pageNumber, GetFriendsResponse getFriendsResponse) {

		String searchStringLower = searchString.toLowerCase();
		List<Player> playerList = playerRepository.searchPlayersByString(searchStringLower, searchStringLower, searchStringLower, playerId);

		if (playerList != null && playerList.size() > 0) {

			UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), playerList);
			List<Player> returnPlayerList = (List<Player>) paginationReturn.getReturnList();

			List<Friend> friends = new ArrayList<Friend>();

			for (Player player : returnPlayerList) {
				Friend friend = new Friend();
				friend.setName(player.getDisplayName());
				friend.setPlayerId(player.getPlayerId());
				friend.setDisplayImageUrl(player.getProfileImageUrl());
				if (player.getProfileImageUrl() != null) {
					friend.setDisplayImagePreSignedUrl(amazonClient.getPreSignUrlFromUrl(player.getProfileImageUrl()));
				}
				friends.add(friend);
			}

			getFriendsResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getFriendsResponse.setMessage(paginationReturn.getReturnMessage());
			getFriendsResponse.setStatus(GetFriendsResponse.StatusEnum.SUCCESS);
			getFriendsResponse.setPageNumber(pageNumber.longValue());
			getFriendsResponse.setPageSize(pageSize.longValue());
			getFriendsResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
			getFriendsResponse.setData(friends);
		} else {
			getFriendsResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getFriendsResponse.setMessage("No records are present for the searchString [" + searchString + "]");
			getFriendsResponse.setStatus(GetFriendsResponse.StatusEnum.FAILURE);
			getFriendsResponse.setPageNumber(pageNumber.longValue());
			getFriendsResponse.setPageSize(pageSize.longValue());
			getFriendsResponse.setTotalNumberofPagesAvailable(0l);
		}

		return getFriendsResponse;

	}

}
