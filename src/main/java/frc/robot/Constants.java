// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public final class SwerveConstants {

    public final class Modules {

      public final class MechanicalConstants {
        // Mechanic measurements
        public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(4);
        public static final double SPEED_MOTOR_GEAR_RATIO = 1.0 / 6.12;
        public static final double ROTATION_MOTOR_GEAR_RATIO = 1.0 / (150.0 / 7.0);
      }

      public final class UnitsConversion {
        // Conversion factors RPM to meters per second (speed)
        public static final double SPEED_ENCODER_ROTATIONS_TO_METERS = MechanicalConstants.SPEED_MOTOR_GEAR_RATIO * Math.PI * MechanicalConstants.WHEEL_DIAMETER_METERS;
        public static final double SPEED_ENCODER_RPM_TO_METERS_PER_SECOND = SPEED_ENCODER_ROTATIONS_TO_METERS / 60;

        // Rotation encoder RPM to radians per second (rotation)
        public static final double ROTATION_ENCODER_ROTATIONS_TO_RADIANS = MechanicalConstants.ROTATION_MOTOR_GEAR_RATIO * 2 * Math.PI;
        public static final double ROTATION_ENCODER_RPM_TO_RADIANS_PER_SECOND = ROTATION_ENCODER_ROTATIONS_TO_RADIANS / 60;
      }

      public final class ModulesRelativeEncodersIDs {
        public static final int FRONT_LEFT_ID = 20;
        public static final int FRONT_RIGHT_ID = 10;
        public static final int BACK_LEFT_ID = 30;
        public static final int BACK_RIGHT_ID = 40;
      }

      public final class SparkIDs {
        /*SPEED MOTORS*/
        public static final int SPEED_FRONT_LEFT_ID = 31;
        public static final int SPEED_FRONT_RIGHT_ID = 42;
        public static final int SPEED_BACK_LEFT_ID = 41;
        public static final int SPEED_BACK_RIGHT_ID = 12;
        
        /*ROTATION MOTORS*/
        public static final int ROTATION_FRONT_LEFT_ID = 22;
        public static final int ROTATION_FRONT_RIGHT_ID = 32;
        public static final int ROTATION_BACK_LEFT_ID = 11;
        public static final int ROTATION_BACK_RIGHT_ID = 2;
      }
      // Declaring PIDs for both Sparks (speed and rotation)
      public final class SparkPID {
        
        public final class Speed {
          public static final double kP = 0.008;
          public static final double kI = 0;
          public static final double kD = 0.012;
          public static final double kFF = 0.2;
        }
        
        public final class Rotation {
          public static final double kP = 0.014;
          public static final double kI = 0;
          public static final double kD = 0.002;
        }
      }
    }

    public final class AbsoluteEncoderID {
      public static final int ABSOLUTE_ENCODER_ID = 1;
    }
  }

}
