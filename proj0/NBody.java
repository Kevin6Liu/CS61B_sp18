/**
*	Simulate a unvierse specified in one of the data files
*/
public class NBody{
/**
*	Given a file name, readRadius returns a double corresponding to the radius of the universe in that file
*/
	public static void main(String[] args){
		/* Store the command line arguments */
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		System.out.println("The duration you entered is " + T);
		System.out.println("The time step you entered is " + dt);
		System.out.println("The address of the data source file is in " + filename);
		System.out.println("Printing the image...");
		/* Read in the data from the source file */
		double u_radius = readRadius(filename);
		Planet[] p_array = readPlanets(filename);
		
		StdDraw.enableDoubleBuffering(); 
		String background = "images/starfield.jpg";
		
		
		
		for(int clk = 0; clk < T; clk += dt){
			double[] xForces = new double[p_array.length];
			double[] yForces = new double[p_array.length];
			/* Draw the background of the universe */
			StdDraw.clear();
			drawBackground(background,u_radius);
			for(int i = 0; i < p_array.length; i++ ){
				Planet p = p_array[i];
				xForces[i] = p.calcNetForceExertedByX(p_array);
				yForces[i] = p.calcNetForceExertedByY(p_array);
			}
			
			for(int j = 0; j < p_array.length; j++ ){
				Planet p = p_array[j];
				p.update(dt,xForces[j],yForces[j]);
			}
			/* Draw the Planets */
			for(Planet p : p_array){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
	}

	public static void drawBackground(String filename, double u_radius){
		StdDraw.setScale(-u_radius,u_radius);
		StdDraw.picture(0,0,filename);
		//StdDraw.show();
		//StdDraw.pause(2000);

	}
	public static double readRadius(String filename){		
		In in = new In(filename);// already a String, no need to add "" 
		int p_number = in.readInt();
		double u_radius = in.readDouble();
		return u_radius;
	}
	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int p_number = in.readInt();
		Planet[] p_array = new Planet[p_number];
		double u_radius = in.readDouble();
		for(int i = 0; i < p_number; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass  = in.readDouble();
			String imgFilename = in.readString();
			p_array[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFilename);// don't forget "new"
		}
		return p_array;
	}

}