package com.nyce.moves.common;

import java.util.List;

public class UtilityFunctions {

	public static class PaginationReturn {

		private int avaialblePages;
		private int totalElements;
		private String returnMessage;
		private List<?> returnList;

		public int getAvaialblePages() {
			return avaialblePages;
		}

		public void setAvaialblePages(int avaialblePages) {
			this.avaialblePages = avaialblePages;
		}

		public int getTotalElements() {
			return totalElements;
		}

		public void setTotalElements(int totalElements) {
			this.totalElements = totalElements;
		}

		public String getReturnMessage() {
			return returnMessage;
		}

		public void setReturnMessage(String returnMessage) {
			this.returnMessage = returnMessage;
		}

		public List<?> getReturnList() {
			return returnList;
		}

		public void setReturnList(List<?> returnList) {
			this.returnList = returnList;
		}

	}

	public static PaginationReturn getPaginatedList(int pageSize, int pageNumber, List<?> list) {

		PaginationReturn paginationReturn = new PaginationReturn();
			
		int numberOfAvailablePages = (list.size() / pageSize) + ((list.size() % pageSize) > 0 ? 1 : 0);
		paginationReturn.setAvaialblePages(numberOfAvailablePages);
		paginationReturn.setTotalElements(list.size());
		
		int startIndex = 0;
		int endIndex = 0;

		if (numberOfAvailablePages < pageNumber) {
			paginationReturn.setReturnMessage("Page number [" + pageNumber + "] is not available, returning the first page. Number of available pages [" + numberOfAvailablePages + "] against [" + list.size() + "] elements with pageSize of [" + pageSize + "]");
			startIndex = 0;
			endIndex = startIndex + pageSize;
		} else if (numberOfAvailablePages == pageNumber) {
			startIndex = (pageNumber * pageSize) - pageSize;
			endIndex = list.size();
			paginationReturn.setReturnMessage("Returning the results of Page number [" + pageNumber + "]. This is the last page. Number of available pages [" + numberOfAvailablePages + "] against [" + list.size() + "] elements with pageSize of [" + pageSize + "]");
		} else {
			startIndex = (pageNumber * pageSize) - pageSize;
			endIndex = startIndex + pageSize;
			paginationReturn.setReturnMessage("Returning the results of Page number [" + pageNumber + "]. Number of available pages [" + numberOfAvailablePages + "] against [" + list.size() + "] elements with pageSize of [" + pageSize + "]");
		}

		paginationReturn.setReturnList(list.subList(startIndex, endIndex));
		
		return paginationReturn;

	}

}
