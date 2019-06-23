import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Parkhausbesitzer {
	private float einnahmen;
	private float maxEinnahme = 0;
	private float zeitSum;
	private Integer p5,p10,p15,p20,p20p; // wieviele Minuten ein Kunde parkt 5min 10min 15min... 20+min
	
	List<Kunde> kundenList = new ArrayList<>();
	
	public Parkhausbesitzer() {
		p5 = p10 = p15 = p20 = p20p = 0;
	}
	
	public void kundeHinzufuegen(Kunde k) {
		kundenList.add(k);
	}
	
	public void kundeVerlaesstParkhaus(Kunde k, Date geparktBis, Long parkdauer, float preis) {
		k.ausfahrt(geparktBis, parkdauer, preis);
		addEinnahmen(k);
		minutenGeparkt(k);
		System.out.println(k);
	}
	
	private void minutenGeparkt(Kunde k) {
		if(k.parkdauer > 0 && k.parkdauer <= 5000) {
			p5++;
		} else if (k.parkdauer > 5000 && k.parkdauer <= 10000) {
			p10++;
		} else if (k.parkdauer > 10000 && k.parkdauer <= 15000) {
			p15++;
		} else if (k.parkdauer > 15000 && k.parkdauer <= 20000) {
			p20++;
		} else if (k.parkdauer > 20000) {
			p20p++;
		} 
	}
	
	public Integer getAnzahlGeparkteMin(int min) {
		switch(min){
			case 5: 
				return this.p5;
			case 10:
				return this.p10;
			case 15:
				return this.p15;
			case 20:
				return this.p20;
			case 25:
				return this.p20p;
			default: return 0;
		}
	}
	
	public float getEinnahmen() {
		/*
		return (float)kundenList.stream()
				.filter(k -> !k.parkend)	// Parkhaus bereits verlassen?
				.mapToDouble(k -> k.preis)	// ermittelter Preis
				.sum();
		*/
		
		return Math.round(einnahmen * 100) / 100.0f;
	}
	
	public float getEinnahmenAvg() {
		return (kundenList.size() == 0) ? 0 : Math.round((getEinnahmen() / kundenList.size()) * 100) / 100.0f;
	}
	
	public void addEinnahmen(Kunde k) {
		this.einnahmen += k.preis;
		this.maxEinnahme = (k.preis > this.maxEinnahme) ? k.preis : this.maxEinnahme;
	}
	
	public float getMaxEinnahme() {
		return Math.round(this.maxEinnahme * 100) / 100.0f;
	}
	
	public float getZeitSum() {
		return this.zeitSum;
	}
	
	public void addZeitSum(Kunde k) {
		this.zeitSum += k.parkdauer;
	}
}
