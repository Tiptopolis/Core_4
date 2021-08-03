package com.uchump.prime.TESTS.ShdrTst;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.Device.iApplet;
import com.uchump.prime.CORE.UI.aViewContext;

public class AlphaCutout implements iApplet{
//an AlphaMask Cutout Shader
	
	// ALPHA CUTOUT WORKS

	Texture texture, mask;
	Color color = new Color();
	SpriteBatch batch;
	OrthographicCamera cam;
	ShaderProgram shader;

	// String VERT =
	// Gdx.files.internal("Test/Shaders/AlphaCutout/AlphaCutoutVertPrototype.glsl").readString();
	// String FRAG =
	// Gdx.files.internal("Test/Shaders/AlphaCutout/AlphaCutoutFragPrototype.glsl").readString();

	String VERT = Gdx.files.internal("Test/Shaders/AlphaCutout/AlphaCutoutVert.glsl").readString();
	String FRAG = Gdx.files.internal("Test/Shaders/AlphaCutout/AlphaCutoutFrag.glsl").readString();

	public AlphaCutout() {
		super();
	}


	
	@Override
	public void create() {
		
		Color x = Color.MAGENTA;
		//Vector3 xVect = new Vector3(x.r, x.g, x.b);
		//Vector3 xVect = new Vector3(0.63f,0.5f,0.35f);
		Vector3 xVect = new Vector3((160f/255f),(130f/255f),(90f/255f));		

		texture = new Texture(Gdx.files.internal("Test/Textures/DeerBaby_east.png"));
		mask = new Texture(Gdx.files.internal("Test/Textures/DeerBaby_eastm.png"));
		
		//tex = GraphicsGenerator.NewSolidColorMaterial(texture, 160, 130, 90);
		// important since we aren't using some uniforms and attributes that SpriteBatch
		// expects
		ShaderProgram.pedantic = false;

		shader = new ShaderProgram(VERT, FRAG);
		if (!shader.isCompiled()) {
			System.err.println(shader.getLog());
			System.exit(0);
		}

		if (shader.getLog().length() != 0)
			System.out.println(shader.getLog());

		
		
		shader.begin();

		shader.setUniformi("u_texture", 0);
		shader.setUniformi("u_mask", 1);
		shader.setUniformf("u_color", xVect); //target color
		
		mask.bind(1);
		texture.bind(0);		
		shader.end();

		batch = new SpriteBatch(1000, shader);
		batch.setShader(shader);

		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.setToOrtho(false);
	}

	
	
	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 100, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setShader(shader);
		batch.begin();
		
		batch.draw(texture, 10, 10);
		batch.draw(texture, 10, 320, 32, 32);
				
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		cam.setToOrtho(false, width, height);
		batch.setProjectionMatrix(cam.combined);
	}

	@Override
	public void dispose() {
		batch.dispose();
		shader.dispose();
	}






	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public aViewContext domain() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
