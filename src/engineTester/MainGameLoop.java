package engineTester;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import loader.Loader;
import loader.OBJloader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		/*
		float[] vertices = {			
				-0.5f,0.5f,0,	
				-0.5f,-0.5f,0,	
				0.5f,-0.5f,0,	
				0.5f,0.5f,0,		
				
				-0.5f,0.5f,1,	
				-0.5f,-0.5f,1,	
				0.5f,-0.5f,1,	
				0.5f,0.5f,1,
				
				0.5f,0.5f,0,	
				0.5f,-0.5f,0,	
				0.5f,-0.5f,1,	
				0.5f,0.5f,1,
				
				-0.5f,0.5f,0,	
				-0.5f,-0.5f,0,	
				-0.5f,-0.5f,1,	
				-0.5f,0.5f,1,
				
				-0.5f,0.5f,1,
				-0.5f,0.5f,0,
				0.5f,0.5f,0,
				0.5f,0.5f,1,
				
				-0.5f,-0.5f,1,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,-0.5f,1
				
		};
		
		float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,			
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0

				
		};
		
		int[] indices = {
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22

		};*/
		
		//RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
		
		  
		//COLOCANDO O chao
		RawModel model = OBJloader.loadObjModel("chao", loader);
				
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("textura")));	
				
		Entity entity = new Entity(staticModel, new Vector3f(0,-4,-55),0,0,0,1);
		  
		
		//COLOCANDO a parede
		RawModel model2 = OBJloader.loadObjModel("par", loader);
				
		TexturedModel staticModel2 = new TexturedModel(model2,new ModelTexture(loader.loadTexture("parede")));	
				
		Entity entity2 = new Entity(staticModel2, new Vector3f(0,-4,-55),0,0,0,1);
		
		
		//COLOCANDO O teto
		RawModel model3 = OBJloader.loadObjModel("chao", loader);
					
		TexturedModel staticModel3 = new TexturedModel(model3,new ModelTexture(loader.loadTexture("textura")));	
						
		Entity entity3 = new Entity(staticModel3, new Vector3f(0,7,-55),0,0,0,1);
		
		
		//COLOCANDO A MONALISA
		RawModel model4 = OBJloader.loadObjModel("monalisa", loader);
		
		TexturedModel staticModel4 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("monalisa")));	
		
		Entity entity4 = new Entity(staticModel4, new Vector3f(42,-4,-63),0,0,0,1);
	
		entity4.increaseRotation(0,-90, 0);
	
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0,0, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			
			renderer.render(entity,shader);
			renderer.render(entity2,shader);
			renderer.render(entity3,shader);
			renderer.render(entity4,shader);
			
			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
