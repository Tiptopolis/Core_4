package com.uchump.prime.TESTS.Raymarching;

import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;

public interface iSDF {

	public <T extends Number> float dst(aVector pReference, aVector center, T size);

	public <T extends iSDF> Class<T> getType();

	public String TypeName();

	public static enum SDF_2D implements iSDF {
		CIRCLE("CIRCLE");

		protected String name;

		private SDF_2D(String name) {
			this.name = name;
		}

		@Override
		public float dst(aVector pRef, aVector center, Number size) {

			if (this.getType().equals(CIRCLE.getClass())) {
				float d = pRef.cpy().sub(center.cpy()).len();
				float c = SDF_2d.sdCircle(pRef.V2(), size);
				return (c - d);

			}
			return 0;
		}

		@Override
		public <T extends iSDF> Class<T> getType() {
			if (this.name == "CIRCLE")
				return (Class<T>) SDF_2D.CIRCLE.getClass();

			return (Class<T>) this.getClass().arrayType();
		}

		@Override
		public String TypeName() {
			return this.name;
		}
	}
}
