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
	
	private Vector3f position = new Vector3f(0,0,0);
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
			position.z -= 0.05f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x+=0.05f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x-=0.05f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z+=0.05f;
		}
		
		
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
