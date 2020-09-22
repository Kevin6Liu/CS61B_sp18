public class TestPlanet{
	public static void main(String[] args){
		Planet Mars = new Planet(0,0,10,10,1,"Mars.gif");
		Planet Saturn = new Planet(10,10,100,100,100,"Saturn.gif");
		System.out.println(Mars.calcForceExertedBy(Saturn));
	}
}