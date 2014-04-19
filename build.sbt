lazy val plot = project in file(".") settings (
              organization :=  "org.improving",
                      name :=  "plot",
               description :=  "paulp's lattice of tightropes",
                   version :=  "1.0.0-SNAPSHOT",
              scalaVersion :=  "2.11.0-RC4",
initialCommands in console :=  "import plot._",
               fork in run :=  true,
 parallelExecution in Test :=  false,
                  licenses :=  Seq("Apache" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
               shellPrompt :=  (s => """%s#%s>""".format(name.value, (Project extract s).currentRef.project)),
               logBuffered :=  false,
       libraryDependencies +=  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  scalacOptions in Compile ++= Seq("-language:*")
)
