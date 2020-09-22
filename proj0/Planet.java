public class Planet{
	public double xxPos;  // Its current x position
	public double yyPos;  // Its current y position
	public double xxVel;  // Its current velocity in the x direction
	public double yyVel;  // Its current velocity in the y direction
	public double mass;  // Its mass
	public String imgFileName;
	static final double Grav_cons = 6.67e-11;
	public Planet(double xP, double yP,
								double xV, double yV,
								double m, String img){
					xxPos = xP;
					yyPos = yP;
					xxVel = xV;
					yyVel = yV;
					mass  = m;
					imgFileName = img;
							}
	public Planet(Planet p){
			/** Constructor method that takes in the data */
			//	 Planet p1 = new Planet(p);// *important*
			this.xxPos = p.xxPos;
			this.yyPos = p.yyPos;
			this.xxVel = p.xxVel;
			this.yyVel = p.yyVel;
		  this.mass = p.mass;
			this.imgFileName = p.imgFileName;
			 }
	 public double calcDistance(Planet p){
		 /** calculate the distance between this instance
		 and another given instance */
		 return Math.sqrt((this.xxPos - p.xxPos)*(this.xxPos - p.xxPos)+(this.yyPos - p.yyPos)*(this.yyPos - p.yyPos));
	 }
	 public double calcForceExertedBy(Planet p){
		 /** calculate the Force p exerted on this planet */
		 double distance = this.calcDistance(p);
		 	return (Grav_cons * this.mass * p.mass)/(distance * distance);
	 }
	 public  double calcForceExertedByX(Planet p){
	 /** calculate the Force p exerted on this planet on the x-axis  */
		 double cos = (p.xxPos - this.xxPos)/this.calcDistance(p);
		 return this.calcForceExertedBy(p)*cos;
	 }
	 public  double calcForceExertedByY(Planet p){
	/** calculate the Force p exerted on this planet on the y-axis  */
		 double sin = (p.yyPos - this.yyPos)/this.calcDistance(p);
		 return this.calcForceExertedBy(p)*sin;
	 }
	 public double calcNetForceExertedByX(Planet[] p){
	/** calculate the net Force []p exerted on this planet on the x-axis  */
		 double sum_Force = 0;
		 for(Planet index : p){// used the "enhanced for loop"
			 if(index == this){
				 continue;
			 }
			else{
				sum_Force = sum_Force + calcForceExertedByX(index);
			}
		 }
		 return sum_Force;
	 }
	 public double calcNetForceExertedByY(Planet[] p){
/** calculate the net Force []p exerted on this planet on the y-axis  */
		 double sum_Force = 0;
		 for(Planet index : p){// used the "enhanced for loop"
			 if(index == this){
				 continue;
			 }
			else{
				sum_Force = sum_Force + calcForceExertedByY(index);
			}
		 }
		 	return sum_Force;
	 }
	 public void update(double dt, double fX, double fY){//标识符不要丢 signature!
	 	double a_X = fX/this.mass;
	 	double a_Y = fY/this.mass;
	 	this.xxVel = this.xxVel + dt * a_X;
	 	this.yyVel = this.yyVel + dt * a_Y;
	 	this.xxPos = this.xxPos + this.xxVel * dt;
	 	this.yyPos = this.yyPos + this.yyVel * dt;
	 }
}
