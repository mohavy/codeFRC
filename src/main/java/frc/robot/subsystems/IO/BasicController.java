// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.IO;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class BasicController extends SubsystemBase {
    public XboxController controller = new XboxController(0);
    /** Creates a new ExampleSubsystem. */
    public BasicController() {}
    
    public double getVertical() {
        switch(Constants.throttleInputPreference) {
            case RIGHT_X:
                return controller.getRightX();
            case RIGHT_Y:
                return controller.getRightY();
            case LEFT_X:
                return controller.getLeftX();
            case LEFT_Y:
                return controller.getLeftY();
        }
        return 0;
    }

    public double getHorizontal() {
        switch(Constants.rotationInputPreference) {
            case RIGHT_X:
                return controller.getRightX();
            case RIGHT_Y:
                return controller.getRightY();
            case LEFT_X:
                return controller.getLeftX();
            case LEFT_Y:
                return controller.getLeftY();
        }
        return 0;
    }

    public void changeThrottlePreference(Constants.ThrottleInputPreference pref) {
        Constants.throttleInputPreference = pref;
    }

    public void changeRotationPreference(Constants.RotationInputPreference pref) {
        Constants.rotationInputPreference = pref;
    }
    
    
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    
    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}
