// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.subsystems.IO.BasicController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Constants
    public static float kHeadlessDriveRotationProportional = 1f;            // Headless PID controller proportional gain
    public static float kHeadlessDriveSpeed = 1f;                           // Drive Speed multiplier for the Headless drive mode
    public static float kSimpleDriveSpeed = 1f;                             // Simple drive speed
    
    // Driver Preferences
    public static enum ThrottleInputPreference { RIGHT_X, RIGHT_Y, LEFT_X, LEFT_Y };  // Stick to get the throttle input from
    public static ThrottleInputPreference throttleInputPreference;
    public static enum RotationInputPreference { RIGHT_X, RIGHT_Y, LEFT_X, LEFT_Y };  // Stick to get the rotation input from
    public static RotationInputPreference rotationInputPreference;
    public static enum SelectedAuto { BASIC_AUTO, ONE_BALL_AUTO };          // Selected auto routine
    public static SelectedAuto selectedAuto;
    public static enum SelectedDrive { BASIC_DRIVE, HEADLESS_DRIVE };       // Selected drive mode
    public static SelectedDrive selectedDrive;
    public static enum SelectedController { ONE_XBOX };                     // Selected controller type
    public static SelectedController selectedController;

    // Global subsystems for re-use.
    // public static BasicController controller;                               // Controller subsystem

}
