package com.uchump.prime.LIB._.Sebastian.Geometry;


import com.badlogic.gdx.math.Vector2;
import com.uchump.prime.CORE.Device.Struct.LinkedList;

import space.earlygrey.simplegraphs.Node;



public class Triangulator {

	 LinkedList<Vertex> vertsInClippedPolygon;
     int[] tris;
     int triIndex;
	
  
     
     // v1 is considered a convex vertex if v0-v1-v2 are wound in a counter-clockwise order.
     boolean IsConvex(Vector2 v0, Vector2 v1, Vector2 v2)
     {
         return Maths2D.SideOfLine(v0, v2, v1) == -1;
     }
     
     public class HoleData
     {
    	 public final int holeIndex;
         public final int bridgeIndex;
         public final Vector2 bridgePoint;

         public HoleData(int holeIndex, int bridgeIndex, Vector2 bridgePoint)
         {
             this.holeIndex = holeIndex;
             this.bridgeIndex = bridgeIndex;
             this.bridgePoint = bridgePoint;
         }
     }
     
     public class Vertex
     {
    	 public final Vector2 position;
    	 public final int index;
    	 public boolean isConvex;
    	 
    	 public Vertex(Vector2 position, int index, boolean isConvex)
    	 {
    		 this.position = position;
             this.index = index;
             this.isConvex = isConvex;
    	 }
     }
}
