public class Planet{
	public double xxPos;  // Its current x position
	public double yyPos;  // Its current y position
	public double xxVel;  // Its current velocity in the x direction
	public double yyVel;  // Its current velocity in the y direction
	public double mass;  // Its mass
	public String imgFileName;
	static final double Grav_cons = 6.67e-11; //Use 'static final' to declare variables like "G" 
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
	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
	}
	 public double calcDistance(Planet p){
		 /** calculate the distance between this instance
		 and another given instance */
		 return Math.sqrt((this.xxPos - p.xxPos)*(this.xxPos - p.xxPos)+(this.yyPos - p.yyPos)*(this.yyPos - p.yyPos));
	 }	// Alternative : Math.pow calculates squaring/exponentiation but will result in a slower code
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
			 if(index.equals(this)){
				 continue;
			 }
			else{
				sum_Force = sum_Force + this.calcForceExertedByX(index);
			}
		 }
		 return sum_Force;
	 }
	 public double calcNetForceExertedByY(Planet[] p){
/** calculate the net Force []p exerted on this planet on the y-axis  */
		 double sum_Force = 0;
		 for(Planet index : p){// used the "enhanced for loop"
			 if(index.equals(this)){
				 continue;
			 }
			else{
				sum_Force = sum_Force + this.calcForceExertedByY(index);
			}
		 }
		 	return sum_Force;
	 }
	 public void update(double dt,double fX,double fY){
	 	double a_net_x,a_net_y = 0;
	 	a_net_x = fX/this.mass;
	 	a_net_y = fY/this.mass;
	 	this.xxVel = this.xxVel + a_net_x*dt;
	 	this.yyVel = this.yyVel + a_net_y*dt;
	 	this.xxPos = this.xxPos +this.xxVel*dt;
	 	this.yyPos = this.yyPos +this.yyVel*dt;

	 }
}
