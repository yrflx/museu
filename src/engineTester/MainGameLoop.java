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
		
		
		  
		//COLOCANDO O CHAO
		RawModel model = OBJloader.loadObjModel("chao", loader);
				
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("textura")));	
				
		Entity entity = new Entity(staticModel, new Vector3f(0,-4,-55),0,0,0,1);
		  
		
		//COLOCANDO A PAREDE
		RawModel model2 = OBJloader.loadObjModel("par", loader);
				
		TexturedModel staticModel2 = new TexturedModel(model2,new ModelTexture(loader.loadTexture("parede")));	
				
		Entity entity2 = new Entity(staticModel2, new Vector3f(0,-4,-55),0,0,0,1);
		
		
		//COLOCANDO O TETO
		RawModel model3 = OBJloader.loadObjModel("chao", loader);
					
		TexturedModel staticModel3 = new TexturedModel(model3,new ModelTexture(loader.loadTexture("teto")));	
						
		Entity entity3 = new Entity(staticModel3, new Vector3f(0,7,-55),0,0,0,1);
		
		
		
		int alturaQuadros = -4;
		int primeiraParede = 5;
		int segundaParede = 25;
		int terceiraParede = 45;
		int quartaParede = 65;
		
		
		int esquerdaParede = 0;
		int direitaParede = 38;
		
		
		/* QUADROS */
		
		RawModel model4 = OBJloader.loadObjModel("quadro", loader);
		
		
		
		
		//QUADRO 0
		TexturedModel quadro0 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("quadro0")));	
		Entity entity4 = new Entity(quadro0, new Vector3f(primeiraParede,alturaQuadros,esquerdaParede),0,0,0,1);
		entity4.increaseRotation(0,-180, 0);
	
		//QUADRO 1	
		TexturedModel quadro1 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("quadro1")));	
		Entity entity5 = new Entity(quadro1, new Vector3f(primeiraParede,alturaQuadros,direitaParede),0,0,0,1);
		entity5.increaseRotation(0,-180, 0);
		
		//QUADRO 2
		TexturedModel quadro2 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("quadro2")));	
		Entity entity6 = new Entity(quadro2, new Vector3f(segundaParede,alturaQuadros,esquerdaParede),0,0,0,1);
		entity6.increaseRotation(0,-180, 0);

		//QUADRO 3
		TexturedModel quadro3 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("quadro3")));	
		Entity entity7 = new Entity(quadro3, new Vector3f(segundaParede,alturaQuadros,direitaParede),0,0,0,1);
		entity7.increaseRotation(0,-180, 0);
		
		//QUADRO 4
		TexturedModel quadro4 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("quadro4")));	
		Entity entity8 = new Entity(quadro4, new Vector3f(terceiraParede,alturaQuadros,esquerdaParede),0,0,0,1);
		entity8.increaseRotation(0,-180, 0);
			
		//QUADRO 5
		TexturedModel quadro5 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("quadro5")));	
		Entity entity9 = new Entity(quadro5, new Vector3f(terceiraParede,alturaQuadros,direitaParede),0,0,0,1);
		entity9.increaseRotation(0,-180, 0);
		
		//QUADRO 6
		TexturedModel quadro6 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("quadro6")));	
		Entity entity10 = new Entity(quadro6, new Vector3f(quartaParede,alturaQuadros,esquerdaParede),0,0,0,1);
		entity10.increaseRotation(0,-180, 0);
			
		
		//QUADRO 7
		TexturedModel quadro7 = new TexturedModel(model4,new ModelTexture(loader.loadTexture("quadro7")));
		Entity entity11 = new Entity(quadro7, new Vector3f(quartaParede,alturaQuadros,direitaParede),0,0,0,1);
		entity11.increaseRotation(0,-180, 0);										
				
		
			
		//construindo a camera;
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
			
			/*quadros*/
			renderer.render(entity4,shader);
			renderer.render(entity5, shader);
			renderer.render(entity6, shader);
			renderer.render(entity7, shader);
			renderer.render(entity8, shader);
			renderer.render(entity9, shader);
			renderer.render(entity10, shader);
			renderer.render(entity11, shader);
			
			
			
			/*/quadros*/
			
			
			shader.stop();
			DisplayManager.updateDisplay();
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
