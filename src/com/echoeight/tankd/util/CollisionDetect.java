package com.echoeight.tankd.util;

public class CollisionDetect {

	public boolean collision;
	
	public CollisionDetect(double obj1x, double obj1y, double obj1height, double obj1width, double obj2x, double obj2y, double obj2height, double obj2width, double angle){
    CollRect tile_rect = new CollRect(obj2x, obj2y, obj2width, obj2height);
    CollRect player_rect = new CollRect(obj1x, obj1y, obj1width, obj1height);

     //Check for rotation
     player_rect.setDegrees(angle);
     collision = player_rect.dumbRotateIntersection(tile_rect);
	}
	
	public boolean doesHit(){
		return collision;
	}

   public class CollRect {
   public double x, y;
   public double x2, y2;
   public double width;
   public double height;
   public double mid_x;
   public double mid_y;
   public double degrees=0;

   
   public CollPoint2D[] rect_points;
   private CollRect() {}
   
   public CollRect(double x, double y, double width, double height) {
      this.width = width;
      this.height = height;
      this.x = x;
      this.y = y;
      x2 = x+width-1;
      y2 = y+height-1;
      mid_x = x+(width/2);
      mid_y = y+(height/2);
      
      //Make the rect_points 
      rect_points = new CollPoint2D[4];
      resetPoints();

   }
   
   public void resetPoints() {
      rect_points[0] = new CollPoint2D(x,y);
      rect_points[1] = new CollPoint2D(x2,y);
      rect_points[2] = new CollPoint2D(x2,y2);
      rect_points[3] = new CollPoint2D(x,y2);
   }
   
   public void setDegrees(double degrees) {
      this.degrees = degrees;
   }
   
   
   public boolean dumbRotateIntersection(CollRect cr2) {
      if (rotatedIntersect(cr2,this)) {
         return true;
      }
      if (rotatedIntersect(this,cr2)) {
         return true;
      }

      return false;
   }
   
   
   private boolean rotatedIntersect(CollRect cr1, CollRect cr2) {
                //Reset points so there is no rotation
      cr1.resetPoints();
      cr2.resetPoints();

                //keep rectangle 1 unrotated, but rotate rectangle 2
      cr2.rotatePoints(cr2.degrees);

                //Since rectangle 1 is unrotated, we need to adjust rectangle to by the opposite of the rotation that didn't happen
      cr2.rotatePoints(-cr1.degrees,cr1.mid_x,cr1.mid_y);

      //Check if any of the points in cr2 intersect cr1
      double cx = cr1.rect_points[0].x;
      double cy = cr1.rect_points[0].y;
      double cx2 = cr1.rect_points[2].x;
      double cy2 = cr1.rect_points[2].y;

      
      for (int i=0;i<4;i++) {
         CollPoint2D p = cr2.rect_points[i];
         if (p.x>=cx&&p.x<=cx2&&
            p.y>=cy&&p.y <=cy2) {
            return true;
         } 
      }
      return false;
   }
   
   
   
   public void rotatePoints(double angle) {
      rotatePoints(angle,mid_x,mid_y);
   }
   
   
   public void rotatePoints(double angle, double mid_x, double mid_y) {
   
      double radians = Math.toRadians(angle);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        double new_x;
        double new_y;
        
        for (int i=0;i<4;i++) {
            new_x = (rect_points[i].x-mid_x)*cos-(rect_points[i].y-mid_y)*sin+mid_x;
            new_y = (rect_points[i].x-mid_x)*sin+(rect_points[i].y-mid_y)*cos+mid_y;
            rect_points[i].x = (int) Math.round(new_x);
            rect_points[i].y = (int) Math.round(new_y);
        }
   }
   
   public boolean intersects(CollRect cr2) {
      CollRect cr = this;
      if (((cr.x>=cr2.x&&cr.x<=cr2.x2)||(cr2.x>=cr.x&&cr2.x<=cr.x2))&&
      ((cr.y>=cr2.y&&cr.y<=cr2.y2)||(cr2.y>=cr.y&&cr2.y<=cr.y2))) {
         return true;
      }
      return false;
   }
   
   
   public CollRect intersection(CollRect cr) {
      CollRect ir = new CollRect();
      if (!intersects(cr)) {return ir;}
      
      //Always use the greatest left edge
      if (cr.x>=x) {
         ir.x = cr.x;
      } else {
         ir.x = x;
      }
      
      //Always use the least right edge
      if (cr.x2<=x2) {
         ir.x2 = cr.x2;
      } else {
         ir.x2 = x2;
      }
      
      //Always use the greatest top edge
      if (cr.y>=y) {
         ir.y = cr.y;
      } else {
         ir.y = y;
      }
      
      //Always use the least bottom edge
      if (cr.y2<=y2) {
         ir.y2 = cr.y2;
      } else {
         ir.y2 = y2;
      }
      
      //Set the size
      ir.width = ir.x2-ir.x;
      ir.height = ir.y2-ir.y;
      
      return ir;
   }
   
}

public class CollPoint2D {
   public double x;
   public double y;
      
   public CollPoint2D(double x, double y) {
      this.x=x;
      this.y=y;
   }
   
   public double magnitude() {
      return Math.sqrt((x*x)+(y*y));
   }
   
   public void normalize() {
      double mag = magnitude();
      x = x / mag;
      y = y / mag;
   }
   
   public double dotProduct(CollPoint2D c2) {
      return ((x*c2.x)+(y*c2.y));
   }
   
}
	
}
