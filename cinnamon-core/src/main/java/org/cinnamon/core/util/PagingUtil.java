package org.cinnamon.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시판에서 필수적이면서 반복적으로 발생하는 페이징을 처리하기 편하게 하기 위해 만들어진 class임.
 * <p>
 * 
 * 
 * @author 신동성
 * @version 1.0
 * 
 */
public class PagingUtil {
	public int ITEM_PER_PAGE = 10; // 한페이지에 표시할 게시물의 갯수(상수)
	public int PAGE_PER_BLOCK = 10; // 페이지리스트에 한번에 표시할 페이지의 갯수(상수)

	private int nowPage; // 0 부터 시작하는 현제 페이지
	private int totalPage; // 1 부터 시작하는 전체 페이
	private long totalRecord;

	private int pageListStart; // 0부터 시작하는 페이지블럭 시작번호
	private int pageListEnd; // 0부터 시작하는 페이지블럭 끝번호

	private int item_per_page = ITEM_PER_PAGE;
	private int page_per_block = PAGE_PER_BLOCK;

	/**
	 * 현재 페이지 번호와 전체 자료갯수를 받아서 여러가지 다른 값들을 설정한다.
	 * 
	 * @param page
	 *            - 현재 페이지번호(1부터시작)
	 * @param size
	 *            - 전체 자료갯
	 */
	public PagingUtil(int page, int pageRecCnt, long size) {
		init(page, pageRecCnt, size);
		// System.out.println(ITEM_PER_PAGE);
	}

	/**
	 * 현재 페이지 번호와 전체 자료갯수를 받아서 여러가지 다른 값들을 설정한다.
	 * 
	 * @param page
	 *            - 현재 페이지번호(1부터시작)
	 * @param size
	 *            - 전체 자료갯
	 */
	public PagingUtil(int page, int pageRecCnt, int pageBlockCnt, long size) {
		init(page, pageRecCnt, pageBlockCnt, size);
		// System.out.println(ITEM_PER_PAGE);
	}

	public PagingUtil() {
	}

	/**
	 * 초기화 메서드
	 * <p>
	 * 현재 페이지 번호와 전체 자료갯수를 받아서 여러가지 다른 값들을 설정한다.
	 * 
	 * @param page
	 *            - 현재 페이지번호(1부터시작)
	 * @param size
	 *            - 전체 자료갯
	 */
	public void init(int page, int pageRecCnt, long size) {
		nowPage = page - 1;
		item_per_page = pageRecCnt;
		totalRecord = size;
		totalPage = (int) Math.ceil((double) totalRecord / item_per_page);

		pageListStart = nowPage / page_per_block * page_per_block;
		pageListEnd = pageListStart + page_per_block - 1;
	}

	/**
	 * 초기화 메서드
	 * <p>
	 * 현재 페이지 번호와 전체 자료갯수를 받아서 여러가지 다른 값들을 설정한다.
	 * 
	 * @param page
	 *            - 현재 페이지번호(1부터시작)
	 * @param size
	 *            - 전체 자료갯
	 */
	public void init(int page, int pageRecCnt, int pageBlockCnt, long size) {
		nowPage = page - 1;
		item_per_page = pageRecCnt;
		page_per_block = pageBlockCnt;
		totalRecord = size;
		totalPage = (int) Math.ceil((double) totalRecord / item_per_page);

		pageListStart = nowPage / page_per_block * page_per_block;
		pageListEnd = pageListStart + page_per_block - 1;
	}

	/**
	 * 페이징 부분에 표시될 페이지 전체
	 * 
	 * @return 표시될 페이지번호를 담고 있는 List객체
	 * @throws Exception
	 *             - 현재 페이지가 표시되는 페이지에서 벋어날 경우 문제가 있음을 알린다.
	 */
	public List<Integer> getPageBlock() throws Exception {
		if (nowPage > totalPage || nowPage < 0) {
			throw new NowPageOutOfBoundException("현제 페이지가 범위를 벋어났습니다: " + nowPage);
		}

		List<Integer> pages = new ArrayList<Integer>();
		for (int i = pageListStart; i <= pageListEnd; i++) {
			if (i > (totalPage - 1)) {
				break;
			}
			// pages.add(String.valueOf(i + 1));
			pages.add(i + 1);
		}
		return pages;
	}

	/**
	 * 첫페이지 번호
	 * 
	 * @return - 1부터 시작하는 첫페이지 번호(1부터 시작함)
	 */
	public int getFirstPage() {
		return 1;
	}

	/**
	 * 이전페이지 번호
	 * 
	 * @return
	 */
	public int getPrevPage() {
		if (nowPage <= 1) {
			return 1;
		} else {
			return nowPage;
		}
	}

	/**
	 * 다음 페이지 번호
	 * 
	 * @return
	 */
	public int getNextPage() {
		if (totalPage == 0) {
			return 1;
		}
		if (nowPage >= totalPage - 1) {
			return totalPage;
		} else {
			return nowPage + 2;
		}
	}

	/**
	 * 마지막 페이지 번호
	 * 
	 * @return
	 */
	public int getLastPage() {
		if (totalPage == 0) {
			return 1;
		} else {
			return totalPage;
		}
	}

	/**
	 * 현재 페이지 번호
	 * 
	 * @return
	 */
	public int getPage() {
		return nowPage + 1;
	}

	/**
	 * 다음 블럭 페이지 번호
	 * 
	 * @return
	 */
	public int getNextBlockPage() {
		if (pageListEnd == 0) {
			return 1;
		}

		int nextBlockPage = pageListEnd + 2;
		if (nextBlockPage > totalPage) {
			return totalPage;
		}

		return pageListEnd + 2;
	}

	/**
	 * 이전블럭 페이지 번호
	 * 
	 * @return
	 */
	public int getPrevBlockPage() {
		if (pageListStart == 0) {
			return 1;
		}

		return (pageListStart - page_per_block) + 1;
	}

	/**
	 * 블럭의 마지막 페이지 번호
	 * 
	 * @return
	 */
	public int getBolckPageListEnd() throws Exception {
		if (pageListEnd > (totalPage - 1)) {
			return totalPage;
		}
		return pageListEnd + 1;
	}

	/*
	 * 아래부터는 태스트용 접근 변수임.-----------------------
	 */

	// getNowPage -> 0 부터 시작하는 page번호
	protected int getNowPage() {
		return nowPage;
	}

	protected void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPageListEnd() {
		return pageListEnd;
	}

	public void setPageListEnd(int pageListEnd) {
		this.pageListEnd = pageListEnd;
	}

	public int getPageListStart() {
		return pageListStart;
	}

	public void setPageListStart(int pageListStart) {
		this.pageListStart = pageListStart;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

//	public static void main(String[] args) {
//		PagingUtil p = new PagingUtil(1, 15, 27);
//
//		System.out.println(p.getFirstPage());
//		System.out.println(p.getPrevPage());
//		System.out.println(p.getPage());
//		System.out.println(p.getNextPage());
//		System.out.println(p.getTotalPage());
//		System.out.println(p.getLastPage());
//	}

	public int getItem_per_page() {
		return item_per_page;
	}

	public void setItem_per_page(int item_per_page) {
		this.item_per_page = item_per_page;
	}

	public int getPage_per_block() {
		return page_per_block;
	}

	public void setPage_per_block(int page_per_block) {
		this.page_per_block = page_per_block;
	}
}

/**
 * 현재 페이지(nowPage)가 표시해야할 페이지블럭밖에 있는 경우에 발생 되는 Exception
 * 
 * @author Adrenalinee
 * 
 */
class NowPageOutOfBoundException extends Exception {
	private static final long serialVersionUID = -2171339047672163116L;

	public NowPageOutOfBoundException(String msg) {
		super(msg);
	}

}
