package com.single.model.dao;

import com.single.model.dto.KakaoMemberDTO;
import com.single.model.dto.MemberDTO;
import com.single.model.dto.NaverMemberDTO;

public interface MemberDAO {

	/*
	 * ȸ������ ����
	 */

	// ȸ������ ó��
	public int memberJoin(MemberDTO member);

	// SNS ȸ������ ó�� (��й�ȣ ���� MEMBER ���̺� INSERT)
	public int memberJoinWithSNS(MemberDTO member);

	// KAKAO ȸ������ ó��
	public int kakaoJoin(KakaoMemberDTO kakao_member);

	// NAVER ȸ������ ó��
	public int naverJoin(NaverMemberDTO naver_member);
	
	// �̸��Ϸ� ȸ����ȣ �������� (SNS ȸ������ �� FK�� ȸ����ȣ�� �ֱ� ����)
	public MemberDTO getMemberCode(String MEMBER_EMAIL);

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
