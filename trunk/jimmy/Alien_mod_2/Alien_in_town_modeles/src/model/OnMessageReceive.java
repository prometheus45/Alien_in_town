package model;

public interface OnMessageReceive {

	//CG messages
	public void onCGChatReceive();
	public void onCGTypeGameReceive();
	public void onCGMaxPlayerReceive();
	public void onCGMinPlayerReceive();
	public void onCGStartGameReceive();
	public void onCGNewGameReceive();
	public void onCGNewVoteReceive();
	public void onCGRemoveVoteReceive();
	
	//CS messages
	public void onCSConnectReceive();
	public void onCSDisconnectReceive();
	public void onCSInscriptionReceive();
	public void onCSIdentificationReceive();
	
	//SG messages
	public void onSGChatReceive();
	public void onSGAddAvatarReceive();
	public void onSGRemoveAvatarReceive();
	
	//SR messages
	public void onSRAvatarsReceive();
	public void onSRStatesReceive();//update
	public void onSRPlayersReceive();
	public void onSRPostsNamesReceive();
	public void onSRTypesReceive();
	public void onSRTimeReceive();
	
	//SP messages
	public void onSPDisconnectReceive();
	
	//SS messages
	public void onSSChatReceive();
	public void onSSConnectReceive();
	public void onSSDisconnectReceive();
	public void onSSLogReceive();
	public void onSSReconnectReceive();
	public void onSSIdentificationReceive();
	public void onSSInscriptionReceive();
	public void onSSNewGameReceive();
	
}
