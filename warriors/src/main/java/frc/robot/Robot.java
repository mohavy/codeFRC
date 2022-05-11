// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.cameraserver.CameraServer;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.GroupMotorControllers;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private static Timer t = new Timer();
  private String m_autoSelected;
  private TalonSRX m_motorRF = new TalonSRX(1);
  private TalonSRX m_motorLB = new TalonSRX(2);
  private TalonSRX m_motorLF = new TalonSRX(3);
  private TalonSRX m_motorRB = new TalonSRX(4);
  private float topSpeed = 1f;
  private float lowSpeed = 0.2f;
  private boolean topSpeedOn = true;
  private Joystick jstick = new Joystick(0);
  TalonSRXConfiguration config = new TalonSRXConfiguration();
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    CameraServer.startAutomaticCapture();
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    config.peakCurrentLimit = 40; // the peak current, in amps
    config.peakCurrentDuration = 1500; // the time at the peak current before the limit triggers, in ms
    config.continuousCurrentLimit = 30; // the current to maintain if the peak limit is triggered
    m_motorRF.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
    m_motorRB.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
    m_motorLF.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
    m_motorLB.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
    CameraServer.startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    
    

  }
  public void setRight(float power) {
    if(topSpeedOn) {
      m_motorRF.set(TalonSRXControlMode.PercentOutput, -power*topSpeed); // Runs at the set power, adjusted for the speed setting
      m_motorRB.set(TalonSRXControlMode.PercentOutput, -power*topSpeed);
    } else {
      m_motorRF.set(TalonSRXControlMode.PercentOutput, -power*lowSpeed); // Runs at the set power, adjusted for the speed setting
      m_motorRB.set(TalonSRXControlMode.PercentOutput, -power*lowSpeed);
    }
    // m_motorRF.set(TalonSRXControlMode.PercentOutput, 0.5); // runs the motor at 50% power
  }

  public void setLeft(float power) {
    if(topSpeedOn) {
      m_motorLF.set(TalonSRXControlMode.PercentOutput, power*topSpeed); // runs the motor at 50% power
      m_motorLB.set(TalonSRXControlMode.PercentOutput, power*topSpeed); // runs the motor at 50% power
    } else {
      m_motorLF.set(TalonSRXControlMode.PercentOutput, power*lowSpeed); // runs the motor at 50% power
      m_motorLB.set(TalonSRXControlMode.PercentOutput, power*lowSpeed); // runs the motor at 50% power
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    setRight((-(float)jstick.getY()-(float)jstick.getX())*0.5f);
    setLeft((-(float)jstick.getY()+(float)jstick.getX())*0.5f);
  }
  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
