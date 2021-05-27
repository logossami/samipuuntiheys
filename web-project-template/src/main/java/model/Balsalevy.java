package model;

public class Balsalevy {

    private long id;
    private double tiheys;
	private double korkeus;
	private double leveys;
	private double paino;
	private double pituus;
	private String grain;

    @SuppressWarnings("unused")
    public Balsalevy() {
    }

    // Konstruktori ID:n kanssa.
    public Balsalevy(long id, double tiheys, double korkeus, double leveys, double paino, double pituus, String grain) {
        this.id = id;
        this.tiheys = tiheys;
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.paino = paino;
        this.pituus = pituus;
        this.grain = grain;
    }
    // Konstruktori ilman ID:tä, getAllItems-metodissa käytetään tätä.
    public Balsalevy( double tiheys, double korkeus, double leveys, double paino, double pituus, String grain) {
        this.tiheys = tiheys;
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.paino = paino;
        this.pituus = pituus;
        this.grain = grain;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public double getTiheys() {
		return tiheys;
	}

	public double getKorkeus() {
		return korkeus;
	}

	public double getLeveys() {
		return leveys;
	}

	public double getPaino() {
		return paino;
	}

	public double getPituus() {
		return pituus;
	}

	public String getGrain() {
		return grain;
	}

	// ToString-metodin tarkempi toteutus puuttuu.
	
	@Override
    public boolean equals(Object other) {
        return other instanceof Balsalevy && ((Balsalevy) other).id == this.id;
    }
}