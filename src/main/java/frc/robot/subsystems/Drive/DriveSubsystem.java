// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.Sensors.Gyro;

import java.lang.Math;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Subsystem to handle all things to do with driving the robot. 
 */
public class DriveSubsystem extends SubsystemBase {
  // Motor setup
  private WPI_TalonSRX m_motorRF = new WPI_TalonSRX(1);
  private WPI_TalonSRX m_motorLB = new WPI_TalonSRX(2);
  private WPI_TalonSRX m_motorLF = new WPI_TalonSRX(3);
  private WPI_TalonSRX m_motorRB = new WPI_TalonSRX(4);
  private TalonSRXConfiguration m_motorConfig = new TalonSRXConfiguration();
  private MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_motorRF, m_motorRB);
  private MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_motorLF, m_motorLB);
  private DifferentialDrive m_differentialDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
  
  private Gyro gyro = new Gyro();
  
  public DriveSubsystem() {
    m_rightGroup.setInverted(true);
    m_motorConfig.peakCurrentLimit = 40; // the peak current, in amps
    m_motorConfig.peakCurrentDuration = 1500; // the time at the peak current before the limit triggers, in ms
    m_motorConfig.continuousCurrentLimit = 30; // the current to maintain if the peak limit is triggered
    m_motorRF.configAllSettings(m_motorConfig);
    m_motorRB.configAllSettings(m_motorConfig);
    m_motorLF.configAllSettings(m_motorConfig);
    m_motorLB.configAllSettings(m_motorConfig);
  }
  /**
   * Drive the robot relative to the robot
   * @param power the forward power
   * @param rotation the rotation power 
   */
  public void simpleDrive(double power, double rotation) {
    m_differentialDrive.arcadeDrive(power, rotation);
  }
  
  /**
   * Stop the motors on the robot.
   */
  public void stopMotors() {
    m_differentialDrive.stopMotor();
  }
  
  /**
   * Drives the robot relative to the direction of the field, not the head of the robot.
   * @param controllerX The 'x' axis value of the controller
   * @param controllerY The 'y' axis value of the controller
   */
  public void headlessDrive(float controllerX, float controllerY) { 
    double error = Math.atan(controllerY/controllerY) - gyro.getGyroYaw();
    double turnPower = error * Constants.kHeadlessDriveRotationProportional;
    m_differentialDrive.arcadeDrive(Math.sqrt(controllerX*controllerX + controllerY*controllerY)*Constants.kHeadlessDriveSpeed,turnPower);
  }
  
  
}
