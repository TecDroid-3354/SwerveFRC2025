package frc.robot.subsystems.Swerve;

import java.util.function.Supplier;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;

import edu.wpi.first.math.Pair;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.util.SwerveDriveUtil.denormalizeLinearVelocity;

public class SwerveDriveDriver {
    private static final double CONTROLLER_DEADBAND = 0.75;

    public enum DriveOrientation {
        FIELD_ORIENTED, ROBOT_ORIENTED
    }

    Supplier<Pair<Double, Double>> leftJoystick;
    Supplier<Pair<Double, Double>> rightJoystick;

    Rotation2d previousDirection = new Rotation2d();

    DriveOrientation orientation = DriveOrientation.FIELD_ORIENTED;

    public SwerveDriveDriver(Supplier<Pair<Double, Double>> leftJoystick, Supplier<Pair<Double, Double>> rightJoystick) {
        this.leftJoystick = leftJoystick;
        this.rightJoystick = rightJoystick;

        ShuffleboardTab tab = Shuffleboard.getTab("Swerve");
        tab.addDouble("Driver Target Angle", () -> previousDirection.getDegrees());

    }

    private ChassisSpeeds obtainTargetSpeeds(Rotation2d currentAngle) {
        Pair<Double, Double> left = leftJoystick.get();

        double vx = denormalizeLinearVelocity(left.getSecond());
        double vy = denormalizeLinearVelocity(left.getFirst());

        if (orientation == DriveOrientation.FIELD_ORIENTED) {
            return ChassisSpeeds.fromRobotRelativeSpeeds(vx, vy, 0.0, currentAngle);
        }

        return new ChassisSpeeds(vx, vy, 0.0);
    }

    private Rotation2d obtainTargetDirection() {
        Pair<Double, Double> right = rightJoystick.get();

        if (!isRotationTargetWithinDeadband(right.getFirst(), -right.getSecond())) {
            return previousDirection;
        }

        Rotation2d target = Rotation2d.fromRadians(atan2(right.getSecond(), right.getFirst()))
                                      .rotateBy(Rotation2d.fromDegrees(90))
                                      .unaryMinus();
        previousDirection = target;

        return target;

    }


    public void apply(SwerveDrive subsystem) {
        ChassisSpeeds speeds = obtainTargetSpeeds(subsystem.getHeading());
        Rotation2d direction = obtainTargetDirection();

        SmartDashboard.putNumber("bbbbbbbb", previousDirection.getDegrees());


        subsystem.drive(speeds, direction);
    }

    public void toggleOrientation() {
        orientation = switch (orientation) {
            case FIELD_ORIENTED -> DriveOrientation.ROBOT_ORIENTED;
            case ROBOT_ORIENTED -> DriveOrientation.FIELD_ORIENTED;
        };
    }

    private boolean isRotationTargetWithinDeadband(double x, double y) {
        return abs(x) > CONTROLLER_DEADBAND || abs(y) > CONTROLLER_DEADBAND;
    }



}