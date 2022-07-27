// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.BasicDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Subsystem to handle all things to do with driving the robot. 
 */
public class DriveSubsystem extends SubsystemBase {

  // Create motor objects and configuration - helps to drive the robot without blowing it up.
  private WPI_TalonSRX m_motorRF;
  private WPI_TalonSRX m_motorLB;
  private WPI_TalonSRX m_motorLF;
  private WPI_TalonSRX m_motorRB;
  private TalonSRXConfiguration m_motorConfig;
  private MotorControllerGroup m_rightGroup;
  private MotorControllerGroup m_leftGroup;
  private DifferentialDrive m_differentialDrive;
  private Joystick joystick = RobotContainer.getJoystick();

  // private Gyro gyro = new Gyro();
  
  public DriveSubsystem() {
    // Create an instance of the parent class. (It's just a Java thing, not gonna lie don't entirely know)
    super();

    /*  Define each of the previously created objects. */

    // Create the motors
    m_motorRF = new WPI_TalonSRX(1);
    m_motorLB = new WPI_TalonSRX(2);
    m_motorLF = new WPI_TalonSRX(3);
    m_motorRB = new WPI_TalonSRX(4);

    // Create motor groups
    // Need to do this so that each side acts as a whole motor, not two separate motors. 
    m_rightGroup = new MotorControllerGroup(m_motorRF, m_motorRB);
    m_rightGroup.setInverted(true); // Ensure that driving the wheels will make the robot go forward, not spin.
    m_leftGroup = new MotorControllerGroup(m_motorLF, m_motorLB);

    // Create the differential drive. Different from mecanum and swerve drives.
    m_differentialDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);

    // Third law of robotics - can't kill itself.
    m_motorConfig = new TalonSRXConfiguration();
    m_motorConfig.peakCurrentLimit = 40; // the peak current, in amps
    m_motorConfig.peakCurrentDuration = 1500; // the time at the peak current before the limit triggers, in ms
    m_motorConfig.continuousCurrentLimit = 30; // the current to maintain if the peak limit is triggered
    m_motorRF.configAllSettings(m_motorConfig);
    m_motorRB.configAllSettings(m_motorConfig);
    m_motorLF.configAllSettings(m_motorConfig);
    m_motorLB.configAllSettings(m_motorConfig);
  }

  /**
   * Set the default command for the robot.
   */
  void initDefaultCommand() {
    setDefaultCommand(new BasicDrive());
  }

  /**
   * Drive the robot relative to the front of the robot
   * @param power the forward power
   * @param rotation the rotation power 
   */
  public void simpleDrive() {
    // Control speed and orientation separately using the controller's X and Y joystick outputs
    m_differentialDrive.arcadeDrive(joystick.getY(), joystick.getX(), true);
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
  // public void headlessDrive(float controllerX, float controllerY) { 
  //   double error = Math.atan2(controllerY, controllerY) - gyro.getGyroYaw();
  //   double turnPower = error * Constants.kHeadlessDriveRotationProportional;
  //   m_differentialDrive.arcadeDrive(Math.sqrt(controllerX*controllerX + controllerY*controllerY)*Constants.kHeadlessDriveSpeed,turnPower);
  // }
  
  
}
