package com.biz.quiz.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.biz.quiz.dao.QuizDao;
import com.biz.quiz.vo.QuizVO;

public class QuizService {

	// DB와 연결하기위해 ibatis의 sessionFactory 선언
	SqlSessionFactory sessionFactory;
	// 키보드 입력을위해 스캐너 선언
	Scanner scan;

	// 클래스 생성자
	public QuizService() {
		// 스캐너 초기화
		scan = new Scanner(System.in);
		// OracleSqlFactory에서 준비된 정보들을 가져와서 sessionFactory에 담는다.
		OracleSqlFactory sqlFactory = new OracleSqlFactory();
		this.sessionFactory = sqlFactory.getSessionFactory();
	}

	// 메인매뉴 method
	public void menu() {
		// 선택할것들을 console창에 보여준다.
		System.out.println("===========================================");
		System.out.println("1.문제입력 2.문제풀이 0.종료");
		System.out.println("-------------------------------------------");
		System.out.print("선택 >>");
		// 키보드 입력을 받아서 1번일 경우 입력 method 2번일경우 풀이 method로 이동
		// 0번이거나 다른값인경우 아무일도 없이 종료.
		int intC = Integer.valueOf(scan.nextLine());
		if (intC == 1) {
			inputMenu();
		}
		if (intC == 2) {
			solveMenu();
		}

	}

	// 문제를 입력 수정 삭제 하는 메뉴 method
	public void inputMenu() {
		// 선택사항들을 console창에 보여준다.
		System.out.println("===========================================");
		System.out.println("1.문제등록 2.문제수정 3.문제삭제 0.종료");
		System.out.println("-------------------------------------------");
		System.out.print("선택 >>");
		// 키보드 입력을 받는다.
		int intC = Integer.valueOf(scan.nextLine());
		if (intC == 1) // 1번일경우 문제등록 method 실행
			insert();
		if (intC == 2) // 2번일경우 문제등록 method 실행
			update();
		if (intC == 3) // 3번일경우 문제등록 method 실행
			delete();
		if (intC == 0) // 0번일경우 이전메뉴로 돌아간다.
			return;
	}

	// 문제풀이 메뉴를 실행하면 시작되는 method
	public void solveMenu() {
		// 전체 문제를 조회해서 그 값을 리턴받아 list에 저장한다. 
		List<QuizVO> quizList = selectAll();
		// 전체 문제를 섞어준다.
		Collections.shuffle(quizList);
		int aCount = 0; // 맞은 문제수
		int nCount = 0; // 틀린 문제수
		int i = 1; // 문제 번호
		// 전체문제중 한문제씩 담아서 vo에 저장하는 반복문 시작
		for (QuizVO vo : quizList) {
			// 문제부분을 불러와서 변수에 저장.
			String strPro = vo.getCb_problem();
			// 정답문항을 불러와서 변수에 저장
			String strAns = vo.getCb_answer();
			// 1번부터 4번까지의 문항을 배열에 담아서 섞어준다.
			String[] strNans = new String[4];
			strNans[0] = vo.getCb_nanswer1();
			strNans[1] = vo.getCb_nanswer2();
			strNans[2] = vo.getCb_nanswer3();
			strNans[3] = vo.getCb_nanswer4();
			Collections.shuffle(Arrays.asList(strNans));
			
			// 문제를 보여줘서 정답을 입력받고 그 문항번호를 리턴받아 저장한다.
			int intC = quizImport(strPro, strNans, i);
			// 입력한 정답의 문항을 문자열 변수에 저장한다.
			String strCheck = strNans[intC];
			// 정답으로 선택한 문항과, 진짜 정답을 비교해서 boolean값으로 리턴받는다.
			if (check(strAns, strCheck)) {
				// 정답일 경우 console창에 보여준후 다음문제 or 종료 선택을 받는다.
				System.out.println("※ 정답입니다.(1 : 다음문제풀기  0 : 종료)");
				// 맞은 문제 +1
				aCount += 1;
				int intNext = Integer.valueOf(scan.nextLine());
				// 종료를 원하면 여태까지 푼 문제와 정답 오답수를 보여준후 종료한다.
				if (intNext == 0) {
					viewAns(i, aCount, nCount);
					return;
				}
			// 정답이 아닌경우	
 			} else {
 				// console창에 다시풀기, 다음문제풀기, 종료 선택사항을 보여준다.
				System.out.println("※ 오답입니다. (1 : 다시풀기  2 : 다음문제풀기  0 : 종료)");
				int intNext = Integer.valueOf(scan.nextLine());
				// 종료를 원하면 틀린문제 +1을 한후 여태까지 푼 문제와 정답 오답수를 보여준후 종료한다.
				if (intNext == 0) {
					nCount += 1;
					viewAns(i, aCount, nCount);
					return;
				}
				// 다시풀기를 원하면 위에서 풀었던 문제를 다시 풀게된다.
				if (intNext == 1) {
					// 같은문제를 풀어서 선택한 문항의 번호를 리턴받는다.
					intC = quizImport(strPro, strNans, i);
					// 그 문항의 내용을 문자열 변수에 저장한다.
					strCheck = strNans[intC];
					// 정답으로 선택한 문항과, 진짜 정답을 비교해서 boolean값으로 리턴받는다.
					if (check(strAns, strCheck)) {
						// 정답일 경우 console창에 보여준후 다음문제 or 종료 선택을 받는다.
						System.out.println("※ 정답입니다.(1 : 다음문제풀기  0 : 종료)");
						// 맞은 문제 +1
						aCount += 1;
						intNext = Integer.valueOf(scan.nextLine());
						// 종료를 원하면 여태까지 푼 문제와 정답 오답수를 보여준후 종료한다.
						if (intNext == 0) {
							viewAns(i, aCount, nCount);
							return;
						}
					// 한번의 기회마저 틀릴경우 다음문제를 풀거나, 종료 선택을 받는다.
					} else {
						System.out.println("※ 오답입니다. (1 : 다음문제풀기  0 : 종료)");
						nCount += 1; // 틀린 문제 +1
						intNext = Integer.valueOf(scan.nextLine());
						// 종료를 원하면 여태까지 푼 문제와 정답 오답수를 보여준후 종료한다.
						if (intNext == 0) {
							viewAns(i, aCount, nCount);
							return;
						}
					}
				}
				// 기회를 버리고 다음문제 푸는것을 선택한 경우
				if(intNext == 2) {
					// 틀린 문제 +1
					// 계속 진행한다.
					nCount += 1;
				}
				
			}
			// 총 푼 문제수가 5의 배수일 경우
			if (i % 5 == 0) {
				// 지금까지 맞춘 문제와 틀린 문제의 개수를 보여준다.
				viewAns(i, aCount, nCount);
			}
			// 한 문제가 마무리되면 문제번호가 + 1 누적된다.
			i += 1 ;
		
		}
		
	}

	// 총 푼 문제, 맞춘문제, 틀린문제, 점수를 계산하여 보여주는 method
	private void viewAns(int i, int aCount, int nCount) {
		System.out.println("푼 문제 : " + i + "개");
		System.out.println("맞춘 문제 : " + aCount + "개");
		System.out.println("틀린 문제 : " + nCount + "개");
		System.out.println("점수 : " + aCount * 5 + "점");
	}
	// 정답으로 입력한 문항과 진짜 정답문항을 비교하여 검사하는 method
	private boolean check(String strAns, String strCheck) {
		if (strAns.equals(strCheck)) {
			return true;
		} else {
			return false;
		}
	}
	// 한개의 문제를 불러와서 보여준뒤 정답을 기입받는 method
	private int quizImport(String strPro, String[] strNans, int i) {

		System.out.println("===========================================");
		System.out.println(i + ". " + strPro);
		System.out.println("-------------------------------------------");
		System.out.println("1. " + strNans[0]);
		System.out.println("2. " + strNans[1]);
		System.out.println("3. " + strNans[2]);
		System.out.println("4. " + strNans[3]);
		System.out.println("-------------------------------------------");
		System.out.print("정답 >>");
		int intC = Integer.valueOf(scan.nextLine());
		// 배열을 사용하기 때문에 입력한값 - 1 을해야 선택한 문항이 된다.
		return intC - 1;
	}
	// 모든 문제들을 불러와서 List로 리턴해주는 method
	private List<QuizVO> selectAll() {
		SqlSession session = this.sessionFactory.openSession();
		QuizDao dao = session.getMapper(QuizDao.class);
		// QuizDao에 있는 selectAll SQL을 실행해서 그값을 리턴받는다.
		List<QuizVO> quizList = dao.selectAll();

		return quizList;
	}
	// 문제를 수정하기위한 update method
	public void update() {
		// 먼저 등록한 문제의 ID값을 조회하여 그 문제를 vo로 리턴받는다.
		QuizVO vo = findByNum();
		// 조회한 번호가 없을경우 종료.
		if (vo == null)
			return;
		// 그 문제의 번호를 넘겨줘서 수정한뒤 다시 vo에 저장한다.
		vo = quizInfoInput(vo.getCb_num());
		// null이 수정된 vo에 저장된경우 종료.
		if (vo == null)
			return;

		SqlSession session = this.sessionFactory.openSession();
		QuizDao dao = session.getMapper(QuizDao.class);
		
		// QuizDao의 update SQL을 실행해서 성공여부(int)값 하나를 리턴받는다.
		int ret = dao.update(vo);
		

		session.commit();
		session.close();
		
		// 리턴받은값이 0 이상인경우 성공 아니면 실패
		if (ret > 0) {
			System.out.println("문제수정 성공");
		} else {
			System.out.println("문제수정 실패");
		}
	}
	// 문제 입력을 위한 insert method
	public void insert() {
		// 종료를 원할때까지 무한으로 입력하는 반복문 시작
		while (true) {
			// 문제 입력을 한후 그 값을 vo에 저장한다.
			QuizVO vo = quizInfoInput(0);
			// 저장된 값이 없는경우 종료.
			if (vo == null)
				return;

			SqlSession session = this.sessionFactory.openSession();
			QuizDao dao = session.getMapper(QuizDao.class);
			
			// QuizDao에 insert SQL을 실행하여 성공여부(int)값 하나를 리턴받는다.
			int ret = dao.insert(vo);

			session.commit();
			session.close();
			
			// 리턴받은값이 0 이상인경우 성공 아니면 실패
			if (ret > 0) {
				System.out.println("문제등록 성공");
			} else {
				System.out.println("문제등록 실패");
			}
		}

	}
	// 문제 삭제를 위한 delete method
	public void delete() {
		// 삭제를 원하는 문제의 ID를 검색하여 그 ID의 문제를 vo에 저장한다.
		QuizVO vo = findByNum();
		// 조회한 번호가 없을경우 종료.
		if (vo == null)
			return;
		
		// 삭제 여부를 console 창에 물어본다.
		System.out.print("정말로 삭제하시겠습니까 (YES, NO) >>");
		String strY = scan.nextLine();
		// YES값을 입력한 경우
		if (strY.equals("YES")) {
			SqlSession session = this.sessionFactory.openSession();
			QuizDao dao = session.getMapper(QuizDao.class);
			// QuizDao에 delete SQL을 실행해서 성공여부(int)값 하나를 리턴받는다.
			int ret = dao.delete(vo.getCb_num());

			session.commit();
			session.close();
			
			// 리턴받은값이 0 이상인경우 성공 아니면 실패
			if (ret > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
		// 삭제를 원하지 않으면 종료.	
		} else {
			return;
		}

	}
	// 문제의 ID를 검색해서 그 문제를 리턴해주는 method
	private QuizVO findByNum() {
		// 조회할 번호를 console에 물어본다.
		System.out.println("=================================");
		System.out.print("조회할 번호 >> ");
		// 그 번호를 int형으로 변환하여 변수에 저장
		int cb_num = Integer.valueOf(scan.nextLine());

		SqlSession session = this.sessionFactory.openSession();
		QuizDao dao = session.getMapper(QuizDao.class);
		
		// QuizDao에 findByNum SQL을 실행하여 그 번호에 해당되는 문제를 vo형태로 리턴한다.
		QuizVO vo = dao.findByNum(cb_num);
		// 만약 null이 리턴되면 검색결과 없음을 보여준후 null을 리턴한다.
		if (vo == null) {
			System.out.println("검색결과 없음");
			return null;
		}
		// 값이 있는경우 그 값들을 console창에 보여주고 리턴해준다.
		System.out.println(vo);
		return vo;

	}
	// 문제를 입력받아서 vo에 저장한뒤 리턴해주는 method
	private QuizVO quizInfoInput(int cb_num) {
		// console에 문제부터 문항까지 입력을 받도록 보여준다.
		System.out.println("=================================");
		System.out.println("문제 등록");
		System.out.println("---------------------------------");
		System.out.print("문제입력(0:종료) >>");
		// 문제를 입력받는다.
		String strPro = scan.nextLine();
		// 종료를 원하면 null값을 리턴한다.
		if (strPro.equals("0"))
			return null;
		// 문제의 문항4개를 저장하기위해 배열 선언
		String[] strAns = new String[4];
		System.out.print("답안1번 >>");
		// 1번문항 저장
		strAns[0] = scan.nextLine();
		System.out.print("답안2번 >>");
		// 2번문항 저장
		strAns[1] = scan.nextLine();
		System.out.print("답안3번 >>");
		// 3번문항 저장
		strAns[2] = scan.nextLine();
		System.out.print("답안4번 >>");
		// 4번문항 저장
		strAns[3] = scan.nextLine();
		System.out.print("정답 문항 >>");
		// 정답이 몇번문항인지 입력받는다.
		int intA = Integer.valueOf(scan.nextLine());
		// 입력받은 값들을 vo에 저장하는데,
		// insert의 경우 DB내의 시퀀스 기능을 활용하기 때문에 ID값을 입력하지 않아도 되지만,
		// update는 ID값의 변화없이 수정하여 저장하기 때문에 ID값을 매개변수로 받아서
		// vo에 저장한다.
		QuizVO vo = new QuizVO(cb_num, strPro, strAns[intA - 1], strAns[0], strAns[1], strAns[2], strAns[3]);
		
		// 저장한 vo를 리턴해준다.
		return vo;
		
	}
}
