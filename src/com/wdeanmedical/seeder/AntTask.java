package com.wdeanmedical.seeder;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class AntTask {
  private static AntTask _instance;
  private Project project;
  private AntTask(Project project) {
    this.project=project;
  }
  public static AntTask instance() {
    if(_instance==null) {
      File buildFile = new File("build.xml");
      Project project=new Project();
      project.setUserProperty("ant.file", buildFile.getAbsolutePath());
      project.init();
      ProjectHelper helper = ProjectHelper.getProjectHelper();
      project.addReference("ant.projectHelper", helper);
      helper.parse(project, buildFile);
      _instance = new AntTask(project);
    }
    return _instance;
  }
  public void setProperty(String name, String value) {
    project.setProperty(name, value);
  }
  public void runTask(String taskName) {
    project.executeTarget(taskName);
  }
}
