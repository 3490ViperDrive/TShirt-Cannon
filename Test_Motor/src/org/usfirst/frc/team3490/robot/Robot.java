/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3490.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	
	Joystick driverStick = new Joystick(0);
	
	// Talon leftFrontDrive = new Talon(9);
	
	Talon leftFrontDrive = new Talon(3);
	Talon leftBackDrive = new Talon(2);
	Talon rightFrontDrive = new Talon(0);
	Talon rightBackDrive = new Talon(1);
	
	Talon arm1 = new Talon(4);
	Talon arm2 = new Talon(5);
	
	Relay rightCannon = new Relay(0);
	Relay leftCannon = new Relay(1);
	
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		
		double leftStickValue = -driverStick.getRawAxis(1);
		double rightStickValue = driverStick.getRawAxis(5);
		
		boolean aButton = driverStick.getRawButton(1);
		boolean bButton = driverStick.getRawButton(2);
		boolean xButton = driverStick.getRawButton(3);
		boolean yButton = driverStick.getRawButton(4);
		
		boolean lBumper = driverStick.getRawButton(5);
		boolean rBumper = driverStick.getRawButton(6);
		
		boolean lFired = false;
		boolean rFired = false;
		
		double secondsToRun = .01;
		double initTime;
		int dummy = 0;
		int dummy2 = 0;
		
		
		/*drive control*/
		leftFrontDrive.set(leftStickValue);
		leftBackDrive.set(leftStickValue);
		rightFrontDrive.set(rightStickValue);
		rightBackDrive.set(rightStickValue);
		
		
		/* arm button control */
		if(aButton && !bButton) {
			arm1.set(.25);
			arm2.set(.25);
		} else if(bButton && !aButton) {
			arm1.set(-.25);
			arm2.set(-.25);
		} else {
			arm1.set(0);
			arm2.set(0);
		}
		
		/*shooting control*/
		if(!lBumper && !rBumper) {
			lFired = false;
			rFired = false;
		}
		if(lBumper && rBumper) {
			SmartDashboard.putNumber("dummy2", dummy2);
			dummy2++;
			if(yButton && !lFired) {
				initTime = Timer.getFPGATimestamp();
				
				leftCannon.set(Relay.Value.kOn);
				while(Timer.getFPGATimestamp() - initTime <= secondsToRun){
					dummy++;
				}
				leftCannon.set(Relay.Value.kOff);
				lFired = true;
			} else {
				leftCannon.set(Relay.Value.kOff);
			}
			if(!lBumper || !rBumper){
				break;
			}
			/*if(xButton && !rFired) {
				initTime = Timer.getFPGATimestamp();
				rightCannon.set(Relay.Value.kOn);
				while(Timer.getFPGATimestamp() - initTime <= secondsToRun){
					dummy++;
				}
				rightCannon.set(Relay.Value.kOff);
				rFired = true;
			} else {
				rightCannon.set(Relay.Value.kOff);
			}*/
			
			
			/*while(xButton || yButton) {
				dummy2++;
				System.out.print(dummy2 + "  ");
				SmartDashboard.putNumber("dummy2", dummy2);
				if(!xButton && !yButton){
					break;
				}
			}*/
			
		}
	}
	

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
