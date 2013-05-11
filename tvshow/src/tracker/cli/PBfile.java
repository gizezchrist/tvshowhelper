package tracker.cli;

public class PBfile {

	/*o*/
	private String filename=null,magnetlink=null,filesize=null,seeders=null,leechers=null;
private int id;

	public PBfile(int id){this.id=id;}
	
	public void setFilename(String var){
		filename=var;
	}
	
	public void setMagnetLink(String var){
		 magnetlink=var;
	}
	
	public void setFilesize(String var){
		 filesize=var;
	}
	public void setLeechers(String var){
		 leechers=var;
	}
	public void setSeeders(String var){
		 seeders=var;
	}
	
	
	public String getFilename(){
		return filename;
	}
	
	public String getMagnetLink(){
		return magnetlink;
	}
	
	public String getFilesize(){
		return filesize;
	}
	public String getLeechers(){
		return leechers;
	}
	public String getSeeders(){
		return seeders;
	}

	public int getID() {
		return this.id;
	}
}
