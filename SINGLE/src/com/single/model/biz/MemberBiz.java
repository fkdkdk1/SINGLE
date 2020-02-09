package com.single.model.biz;

import com.single.model.dto.KakaoMemberDTO;
import com.single.model.dto.MemberDTO;
import com.single.model.dto.NaverMemberDTO;

public interface MemberBiz {

	/*
	 * ȸ������ ����
	 */
	
	// ȸ������ ó��
	public int memberJoin(MemberDTO dto);

	// KAKAO ȸ������ ó��
	public int kakaoJoin(KakaoMemberDTO kakao_member);

	// NAVER ȸ������ ó��
	public int naverJoin(NaverMemberDTO naver_member);
	
	// �̸��� �ߺ� üũ
	public int emailCheck(String NEW_EMAIL);
	
	// ���� �ߺ� üũ
	public int nicknameCheck(String NEW_NICKNAME);
	
	
	/*
	 * �α��� ����
	 */
	
	// �α��� ó��
	public MemberDTO memberLogin(MemberDTO member);
	
	// �α��� �� ȸ���� ���� ��ȸ
	public MemberDTO loginMember(int MEMBER_CODE);
}
