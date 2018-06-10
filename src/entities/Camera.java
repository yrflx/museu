package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	
	private static final float RUN_SPEED = 20;
	private static final float TURN_SPEED = 160;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;	
	private float angleAroundPlayer = 0;
	
	private Vector3f position = new Vector3f(-35,0,-55);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(){}
	
	public void move(){
		
		//calculateZoom();
		//float horizontalDistance = calculateHorizontalDistance();
		//float verticalDistance = calculateVerticalDistance();
		//calculateCameraPosition(horizontalDistance, verticalDistance);
		
		//calcula o angulo da câmera em relação ao chão e a rotação na horizontal;
		calculatePitch();
		calculateAngleAroundPlayer();
		this.yaw = 180 - angleAroundPlayer;
		
		//movimentação pelo teclado;
		checkInputs();
		
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			if((checkCollision(position.x,position.z))==1){
				position.x-= 1.0f;
			}
			else {
			position.x+= 0.05f;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			if((checkCollision(position.x,position.z))==1){
				position.z+= 1.0f;
			}else {
			position.z-= 0.05f;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			if((checkCollision(position.x,position.z))==1){
				position.x+= 1.0f;
			}
			else {
			position.x-= 0.05f;
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			if((checkCollision(position.x,position.z))==1){
				position.z-=1.0f;
			}
			else {
			position.z+=0.05f;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_P)){    // feedback da posição da camera
			System.out.println("x: "+position.x);
			System.out.println("y: "+position.y);
			System.out.println("z: "+position.z);
			
		}
	}
	
	public int checkCollision(float x2, float z2) {     // checa as colisões
		float x = x2;
		float z = z2;
		if((z<-56) || (z>0) || (z>-48 && x<-19) || (x>59) || (x<-39)){  // checa colisões das parede maiores da sala
			return 1;
		}
		if(x>-0.6 && x<1.7 && (z>-23 || z<-34)) {    // parede que separa os quadros
			return 1; 
		}
		if(x>19.5 && x<21.5 && (z>-23 || z<-34)) {  // parede que separa os quadros
			return 1;
		}
		if(x>39.5 && x<41.5 && (z>-23 || z<-34)) {  // parede que separa os quadros
			return 1;
		}
		return 0;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	
	/**	(caso exista um personagem);
	private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(angleAroundPlayer)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(angleAroundPlayer)));
		position.x = position.getX() - offsetX; 
		position.y = position.getY() + verticalDistance; 
		position.z = position.getZ() - offsetZ; 
	}
	
	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	**/
	
	
	/**
	 * aplica zoom na tela; (no caso do nosso projeto nao será utilizado);
	 */
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
	}
	
	/**
	 * Calcula o angulo que a camera está em relação do solo,
	 * permitindo mover a camera para cima e para baixo com o click Direito + movimentação do mouse;
	 */
	private void calculatePitch() {
		if(Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
	}
	
	/**
	 * Calcula o Angulo na horizontal,
	 * permitindo girar a câmera para direita e esquerda com o click+movimentação do mouse;
	 */
	private void calculateAngleAroundPlayer() {
		if(Mouse.isButtonDown(0)) {
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChange;
		}
	}
	
	public void checkInputs() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = RUN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -RUN_SPEED;
		}else {
			this.currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			this.currentTurnSpeed = TURN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			this.currentTurnSpeed = -TURN_SPEED;
		}else {
			this.currentTurnSpeed = 0;
		}
	}

}
