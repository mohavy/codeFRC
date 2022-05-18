package frc.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Gyro extends SubsystemBase {
    AHRS ahrs;
    public Gyro() {
        ahrs = new AHRS(SPI.Port.kMXP); 
    }
    void resetGyroYaw() {
        ahrs.reset();
    }
    public double getGyroYaw() {
        return ahrs.getAngle();
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Gyro", getGyroYaw());
    }
}
