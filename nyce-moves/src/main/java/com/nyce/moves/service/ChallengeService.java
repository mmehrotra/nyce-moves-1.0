package com.nyce.moves.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyce.moves.common.ApplicationConstants;
import com.nyce.moves.common.UtilityFunctions;
import com.nyce.moves.model.Challenge;
import com.nyce.moves.model.Challenge.ChallengeStatusEnum;
import com.nyce.moves.model.ChallengeParticipants;
import com.nyce.moves.model.ChallengeRequest;
import com.nyce.moves.model.CreateChallengeResponse;
import com.nyce.moves.model.GetAllChallengesResponse;
import com.nyce.moves.model.GetChallengeResponse;
import com.nyce.moves.model.LightweightChallenge;
import com.nyce.moves.model.Player;
import com.nyce.moves.model.ResponseTemplate;
import com.nyce.moves.model.ResponseTemplate.StatusEnum;
import com.nyce.moves.repository.ChallengeRepository;
import com.nyce.moves.repository.PlayerRepository;

@Service
public class ChallengeService {

	@Autowired
	ChallengeRepository challengeRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	PlayerService playerService;

	@Autowired
	private AmazonClient amazonClient;

	public ResponseTemplate closeChallenge(Long challengeOwner, Long challengeId, String challengeName, ResponseTemplate responseTemplate) {

		Challenge challenge = null;

		if (challengeId != null && challengeId != 0L) {
			challenge = challengeRepository.findOne(challengeId);
		} else if (challengeName != null && challengeName != "") {
			List<Challenge> challenges = challengeRepository.findByChallangeName(challengeName);
			for (Challenge challengeInList : challenges) {
				if (challengeInList.getChallangeCreatedBy().longValue() == challengeOwner.longValue()) {
					challenge = challengeInList;
					break;
				}
			}
		}
		if (challenge != null) {
			if (challenge.getChallangeCreatedBy().longValue() == challengeOwner.longValue()) {
				challenge.setChallengeStatus(ChallengeStatusEnum.CLOSED);
				Challenge returnChallenge = challengeRepository.save(challenge);
				responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
				responseTemplate.setMessage("Challenge with challenge Id [" + returnChallenge.getChallangeId() + "] has been closed");
				responseTemplate.setStatus(StatusEnum.SUCCESS);
			} else {
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
				responseTemplate.setMessage("Challenge with challenge Id [" + challenge.getChallangeId() + "] can not be closed by player [" + challengeOwner + "], since he is not owner of the challenge");
				responseTemplate.setStatus(StatusEnum.FAILURE);
			}
		} else {
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setMessage("Challenge with challenge Id [" + challengeId + "] or challenge with challengeName [" + challengeName + "] does not exist");
			responseTemplate.setStatus(StatusEnum.FAILURE);
		}

		return responseTemplate;

	}

	public GetChallengeResponse getChallenge(Long challengeId, String challengeName, GetChallengeResponse getChallengeResponse) {

		Challenge challenge = null;

		if (challengeId != null && challengeId != 0L) {
			challenge = challengeRepository.findOne(challengeId);
		} else if (challengeName != null && challengeName != "") {
			List<Challenge> challenges = challengeRepository.findByChallangeName(challengeName);
			if (challenges != null && challenges.size() > 0) {
				challenge = challenges.get(0);
			}
		}
		if (challenge != null) {
			getChallengeResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
			getChallengeResponse.setMessage("Challenge with id [" + challenge.getChallangeId() + "] has been successfully fetched");
			getChallengeResponse.setStatus(GetChallengeResponse.StatusEnum.SUCCESS);
			getChallengeResponse.setData(populatePreSignedUrls(challenge));
		} else {
			getChallengeResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			getChallengeResponse.setMessage("Challenge with id [" + challengeId + "] or challengeName [" + challengeName + "] does not exist into the system");
			getChallengeResponse.setStatus(GetChallengeResponse.StatusEnum.FAILURE);
		}

		return getChallengeResponse;

	}

	public GetAllChallengesResponse getAllChallengesOfAPlayer(Long playerId, String challengeStatus, BigDecimal pageSize, BigDecimal pageNumber, GetAllChallengesResponse getAllChallengesResponse) {

		Player player = playerRepository.findOne(playerId);

		if (player != null && player.getLightweightChallenges() != null && player.getLightweightChallenges().size() > 0) {

			List<LightweightChallenge> challenges = player.getLightweightChallenges();
			List<Long> challengeIdList = new ArrayList<Long>();

			if (challenges != null && challenges.size() > 0) {
				for (LightweightChallenge lChallenge : challenges) {
					challengeIdList.add(lChallenge.getChallangeId());
				}

				List<Challenge> nonFilteredchallengeList = challengeRepository.fetchChallengesByChallengeIdList(challengeIdList);
				List<Challenge> challengeList = new ArrayList<Challenge>();

				if (nonFilteredchallengeList != null && nonFilteredchallengeList.size() > 0 && challengeStatus != null) {
					for (Challenge challengeFromList : nonFilteredchallengeList) {
						if (challengeStatus.equalsIgnoreCase("ALL") || challengeFromList.getChallengeStatus().toString().equalsIgnoreCase(challengeStatus)) {
							challengeList.add(challengeFromList);
						}
					}
				}

				if (challengeList != null && challengeList.size() > 0) {
					challengeList.sort((Challenge c1, Challenge c2) -> c2.getChallanageCreationTime().compareTo(c2.getChallanageCreationTime()));
					UtilityFunctions.PaginationReturn paginationReturn = UtilityFunctions.getPaginatedList(pageSize.intValue(), pageNumber.intValue(), challengeList);
					getAllChallengesResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					getAllChallengesResponse.setMessage(paginationReturn.getReturnMessage());
					getAllChallengesResponse.setStatus(GetAllChallengesResponse.StatusEnum.SUCCESS);
					getAllChallengesResponse.setPageNumber(pageNumber.longValue());
					getAllChallengesResponse.setPageSize(pageSize.longValue());
					getAllChallengesResponse.setTotalNumberofPagesAvailable(new Long(paginationReturn.getAvaialblePages()));
					getAllChallengesResponse.setData(populatePreSignedUrlInChallengeList((List<Challenge>) paginationReturn.getReturnList()));
				} else {
					getAllChallengesResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
					getAllChallengesResponse.setMessage("No records are present for the playerId [" + playerId + "]");
					getAllChallengesResponse.setStatus(GetAllChallengesResponse.StatusEnum.FAILURE);
					getAllChallengesResponse.setPageNumber(pageNumber.longValue());
					getAllChallengesResponse.setPageSize(pageSize.longValue());
					getAllChallengesResponse.setTotalNumberofPagesAvailable(0l);
				}

			} else {
				getAllChallengesResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
				getAllChallengesResponse.setMessage("No records are present for the playerId [" + playerId + "]");
				getAllChallengesResponse.setStatus(GetAllChallengesResponse.StatusEnum.FAILURE);
				getAllChallengesResponse.setPageNumber(pageNumber.longValue());
				getAllChallengesResponse.setPageSize(pageSize.longValue());
				getAllChallengesResponse.setTotalNumberofPagesAvailable(0l);

			}

		}

		return getAllChallengesResponse;
	}

	public ResponseTemplate joinChallenge(Long playerId, Long challengeId, String challengeName, ResponseTemplate responseTemplate) {
		Challenge challenge = null;

		if (challengeId != null && challengeId != 0L) {
			challenge = challengeRepository.findOne(challengeId);
		} else if (challengeName != null && challengeName != "") {
			List<Challenge> challenges = challengeRepository.findByChallangeName(challengeName);
			if (challenges != null && challenges.size() > 0) {
				challenge = challenges.get(0);
			}
		}

		if (challenge != null && challenge.getChallengeStatus().compareTo(ChallengeStatusEnum.CLOSED) != 0) {
			Player player = playerRepository.findOne(playerId);

			if (player != null) {
				List<ChallengeParticipants> friends = challenge.getChallengeParticipants();
				boolean existingPlayer = false;

				for (ChallengeParticipants friend : friends) {
					if (friend.getPlayerId() == playerId) {
						existingPlayer = true;
						break;
					}
				}
				if (!existingPlayer) {
					ChallengeParticipants friend = new ChallengeParticipants();
					friend.setPlayerId(player.getPlayerId());
					friend.setName(player.getDisplayName());
					friend.setDisplayImageUrl(player.getProfileImageUrl());
					friends.add(friend);

					challenge.setChallengeParticipants(friends);
					challengeRepository.save(challenge);

					List<LightweightChallenge> challengesFromPlayer = player.getLightweightChallenges();
					LightweightChallenge lightweightChallenge = new LightweightChallenge();
					lightweightChallenge.setChallangeId(challenge.getChallangeId());
					lightweightChallenge.setChallangeName(challenge.getChallangeName());
					challengesFromPlayer.add(lightweightChallenge);
					player.setLightweightChallenges(challengesFromPlayer);
					playerRepository.save(player);

					responseTemplate.setCode(ApplicationConstants.SUCCESS_CODE_11001);
					responseTemplate.setMessage("Player with playerId [" + playerId + "] has joined the challenge with challenge Id [" + challenge.getChallangeId() + "]");
					responseTemplate.setStatus(StatusEnum.SUCCESS);
				} else {
					responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
					responseTemplate.setMessage("Player with playerId [" + playerId + "] has already joined the challenge with challenge Id [" + challenge.getChallangeId() + "]");
					responseTemplate.setStatus(StatusEnum.FAILURE);
				}
			} else {
				responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
				responseTemplate.setMessage("Player with playerId [" + playerId + "] does not exist in the system");
				responseTemplate.setStatus(StatusEnum.FAILURE);
			}
		} else {
			responseTemplate.setCode(ApplicationConstants.FAILURE_CODE_31001);
			responseTemplate.setMessage("Challenge with id [" + challengeId + "] or challengeName [" + challengeName + "] does not exist into the system or already closed");
			responseTemplate.setStatus(StatusEnum.FAILURE);
		}

		return responseTemplate;

	}

	public CreateChallengeResponse submitChallenge(Long playerId, ChallengeRequest challengeRequest, CreateChallengeResponse createChallengeResponse) {

		Player player = playerRepository.findOne(playerId);

		if (player != null && challengeRequest.getChallangeName() != null && challengeRequest.getChallangeName().trim() != "") {
			Challenge challenge = playerService.createChallenge(challengeRequest.getChallangeName(), player);
			playerRepository.save(player);

			if (challenge != null) {
				createChallengeResponse.setCode(ApplicationConstants.SUCCESS_CODE_11001);
				createChallengeResponse.setMessage("Challenge with id [" + challenge.getChallangeId() + "] has been successfully created");
				createChallengeResponse.setStatus(CreateChallengeResponse.StatusEnum.SUCCESS);
				createChallengeResponse.setData(populatePreSignedUrls(challenge));
			} else {
				createChallengeResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
				createChallengeResponse.setMessage("Challenge was not created successfully");
				createChallengeResponse.setStatus(CreateChallengeResponse.StatusEnum.FAILURE);
			}

		} else {
			createChallengeResponse.setCode(ApplicationConstants.FAILURE_CODE_31001);
			createChallengeResponse.setMessage("Challenge was not created successfully");
			createChallengeResponse.setStatus(CreateChallengeResponse.StatusEnum.FAILURE);
		}

		return createChallengeResponse;
	}

	public List<Challenge> populatePreSignedUrlInChallengeList(List<Challenge> challengeList) {

		for (Challenge challenge : challengeList) {
			challenge = populatePreSignedUrls(challenge);
		}
		return challengeList;
	}

	public Challenge populatePreSignedUrls(Challenge challenge) {

		if (challenge != null) {

			if (challenge.getOwnerProfileImageUrl() != null && challenge.getOwnerProfileImageUrl() != "") {
				String objectName = amazonClient.getObjectNameFromS3Url(challenge.getOwnerProfileImageUrl());
				String presignedUrl = amazonClient.generatePreSignedUrl(objectName);
				challenge.setProfilePreSignUrl(presignedUrl);
			}

			for (ChallengeParticipants friend : challenge.getChallengeParticipants()) {
				if (friend.getDisplayImageUrl() != null && friend.getDisplayImageUrl() != "") {
					String objectName = amazonClient.getObjectNameFromS3Url(friend.getDisplayImageUrl());
					String presignedUrl = amazonClient.generatePreSignedUrl(objectName);
					friend.setDisplayImagePreSignedUrl(presignedUrl);
				}
			}

		}

		return challenge;
	}

}
