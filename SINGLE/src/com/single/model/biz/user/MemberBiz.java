package com.single.model.biz.user;

import com.single.model.dto.user.KakaoMemberDTO;
import com.single.model.dto.user.MemberDTO;
import com.single.model.dto.user.NaverMemberDTO;

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
