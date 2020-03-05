package com.single.model.dto.chat;

import java.util.Date;

public class ChatMessageDTO {

	private int CHATROOM_CODE;
	private int CHATMESSAGE_CODE;
	private String CHATMESSAGE_CONTENT;
	private Date CHATMESSAGE_SENDDATE;
	private int CHATMESSAGE_FROM;
	private int CHATMESSAGE_TO_COUNT;

	public ChatMessageDTO() {
	}

	public ChatMessageDTO(int cHATROOM_CODE, int cHATMESSAGE_CODE, String cHATMESSAGE_CONTENT,
			Date cHATMESSAGE_SENDDATE, int cHATMESSAGE_FROM, int cHATMESSAGE_TO_COUNT) {
		CHATROOM_CODE = cHATROOM_CODE;
		CHATMESSAGE_CODE = cHATMESSAGE_CODE;
		CHATMESSAGE_CONTENT = cHATMESSAGE_CONTENT;
		CHATMESSAGE_SENDDATE = cHATMESSAGE_SENDDATE;
		CHATMESSAGE_FROM = cHATMESSAGE_FROM;
		CHATMESSAGE_TO_COUNT = cHATMESSAGE_TO_COUNT;
	}

	@Override
	public String toString() {
		return "ChatMessageDTO [CHATROOM_CODE=" + CHATROOM_CODE + ", CHATMESSAGE_CODE=" + CHATMESSAGE_CODE
				+ ", CHATMESSAGE_CONTENT=" + CHATMESSAGE_CONTENT + ", CHATMESSAGE_SENDDATE=" + CHATMESSAGE_SENDDATE
				+ ", CHATMESSAGE_FROM=" + CHATMESSAGE_FROM + ", CHATMESSAGE_TO_COUNT=" + CHATMESSAGE_TO_COUNT + "]";
	}

	public int getCHATROOM_CODE() {
		return CHATROOM_CODE;
	}

	public void setCHATROOM_CODE(int cHATROOM_CODE) {
		CHATROOM_CODE = cHATROOM_CODE;
	}

	public int getCHATMESSAGE_CODE() {
		return CHATMESSAGE_CODE;
	}

	public void setCHATMESSAGE_CODE(int cHATMESSAGE_CODE) {
		CHATMESSAGE_CODE = cHATMESSAGE_CODE;
	}

	public String getCHATMESSAGE_CONTENT() {
		return CHATMESSAGE_CONTENT;
	}

	public void setCHATMESSAGE_CONTENT(String cHATMESSAGE_CONTENT) {
		CHATMESSAGE_CONTENT = cHATMESSAGE_CONTENT;
	}

	public Date getCHATMESSAGE_SENDDATE() {
		return CHATMESSAGE_SENDDATE;
	}

	public void setCHATMESSAGE_SENDDATE(Date cHATMESSAGE_SENDDATE) {
		CHATMESSAGE_SENDDATE = cHATMESSAGE_SENDDATE;
	}

	public int getCHATMESSAGE_FROM() {
		return CHATMESSAGE_FROM;
	}

	public void setCHATMESSAGE_FROM(int cHATMESSAGE_FROM) {
		CHATMESSAGE_FROM = cHATMESSAGE_FROM;
	}

	public int getCHATMESSAGE_TO_COUNT() {
		return CHATMESSAGE_TO_COUNT;
	}

	public void setCHATMESSAGE_TO_COUNT(int cHATMESSAGE_TO_COUNT) {
		CHATMESSAGE_TO_COUNT = cHATMESSAGE_TO_COUNT;
	}

}
