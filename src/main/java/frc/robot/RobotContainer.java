// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.BasicDrive;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  public static final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  // Auto commands

  // Drive Commands 

  // OI devices
  static private Joystick joystick = new Joystick(0);
    
  // Return OI devices
  static public Joystick getJoystick() {
      return joystick;
  }

  // private final BasicDrive m_basicDrive = new BasicDrive(m_driveSubsystem, Constants.controller.getVertical(), Constants.controller.getHorizontal());

  /** 
   * The container for the robot. Contains subsystems, OI devices, and commands. 
   * */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_driveSubsystem.setDefaultCommand(new BasicDrive());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
