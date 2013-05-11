package tracker.cli;

public class XDCCfile {

	
	private String filename=null,link=null,filesize=null,dw=null,channel=null;
	private int id;

	public XDCCfile(int id){
		this.id=id;
	}
	
	public int getID(){
		return id;
	}
	
	public void setChannel(String var){
		channel=var;
	}
	
	public String getChannel(){
		return channel;
	}
	
	public void setFilename(String var){
		filename=var;
	}
	
	public void setLink(String var){
		 link=var;
	}
	
	public void setFilesize(String var){
		 filesize=var;
	}

	public void setDW(String var){
		 dw=var;
	}
	
	
	public String getFilename(){
		return filename;
	}
	
	public String getLink(){
		return link;
	}
	
	public String getFilesize(){
		return filesize;
	}
	public String getDW(){
		return dw;
	}
}
