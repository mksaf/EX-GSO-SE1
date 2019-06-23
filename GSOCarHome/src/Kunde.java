import java.util.Date;

public class Kunde {
	public Integer nr;
	public Date geparktAm;
	public Date geparktBis;
	public Long parkdauer;
	public String ticket;
	public float preis;
	public Boolean parkend;
	
	public Kunde(Integer nr, Date geparktAm, String ticket) {
		einfahrt(nr, geparktAm, ticket);
		System.out.println(this);
	}
	
	public void einfahrt(Integer nr, Date geparktAm, String ticket) {
		this.nr = nr;
		this.geparktAm = geparktAm;
		this.ticket = ticket;
		this.parkend = true;
	}
	
	public void ausfahrt(Date geparktBis, Long parkdauer, float preis) {
		this.geparktBis = geparktBis;
		this.parkdauer = parkdauer;
		this.preis = preis;
		this.parkend = false;
	}
	
	@Override
	public String toString() {
		String ret;
		if(parkend) {
			ret = "Kunde: Nr=" + nr + "\n"
					+ "geparkt am=" + geparktAm.toString() + "\n"
					+ "ticket=" + ticket + "\n"
					+ "parkend=" + parkend + "\n";
		} else {
			ret = "Kunde: Nr=" + nr + "\n"
					+ "geparkt am=" + geparktAm.toString() + "\n"
					+ "geparkt bis=" + geparktBis.toString() + "\n"
					+ "parkdauer=" + parkdauer + "\n"
					+ "ticket=" + ticket + "\n"
					+ "preis=" + preis + "\n"
					+ "parkend=" + parkend + "\n";
		}
		return ret;
	}
}
