/*
 * Copyright (C) 2015  Language Technology Group
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package divid

import com.typesafe.sbt.SbtScalariform.scalariformSettings
import sbt.Keys._
import sbt._


object DividBuild extends Build {

	lazy val commonSettings = scalariformSettings ++ Dependencies.Versions ++ Seq(
		version := "0.0.1-SNAPSHOT",
		organization := "de.tudarmstadt.lt"
	)

	// Run all tasks on both projects
	lazy val root = project.in(file(".")).aggregate(core, common)
	// Will put common project on the classpath of core
	lazy val core = project.dependsOn(common % "test->test;compile->compile").settings(commonSettings: _*)
	// Name is used to reference folder and project at the command line
	lazy val common = project.settings(commonSettings: _*)
}
