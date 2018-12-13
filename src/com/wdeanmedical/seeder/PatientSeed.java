package com.wdeanmedical.seeder;

import com.wdeanmedical.core.Core;
import com.wdeanmedical.dto.PatientDTO;
import com.wdeanmedical.entity.Client;
import com.wdeanmedical.entity.Guardian;
import com.wdeanmedical.entity.Patient;
import com.wdeanmedical.entity.PatientClinician;
import com.wdeanmedical.entity.RecoveryCode;
import com.wdeanmedical.entity.User;
import com.wdeanmedical.factory.GuardianFactory;
import com.wdeanmedical.factory.PatientFactory;
import com.wdeanmedical.persistence.AppDAO;
import com.wdeanmedical.persistence.PatientDAO;
import com.wdeanmedical.util.DataEncryptor;
import com.wdeanmedical.util.JSONUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.StatelessSession;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class PatientSeed {

  static Logger logger = Logger.getLogger(PatientSeed.class.getName());

  private class DOB{
    public String dob;
    public Integer age;
    public DOB(String dob, Integer age) {
      this.dob=dob;
      this.age = age;
    }
  }
  public static Integer MAX_PATIENTS=100;
  private static Integer MALE=1;
  private static Integer FEMALE=2;	
  private static final Integer PATIENT_CLINICIAN_ID = 2;
  private PatientDAO patientDAO;
  private Properties props;
  private Random rnd;
  private static Integer[] GENDERS= {MALE,FEMALE};
  private static String TESTER="Tester";
  private static Integer MAX_MINOR_AGE=17;
  private static String PATIENT_AGE_RANGE="10-80";
  private static String[] GUARDIAN_AGE_RANGE= {"18","80"};

  public PatientSeed(PatientDAO patientDAO, Properties props) {
    this.patientDAO=patientDAO;
    this.props = props;
    this.rnd = new Random();
  }	
  private <T> T getRandom(T[] array) {
    int randomIndex = this.rnd.nextInt(array.length);
    return array[randomIndex];
  }
  private String[] getProp(String prop) {
    String[] props=this.props.get(prop).toString().split(",");
    return props;
  }
  private String getEmail(PatientDTO dto) {
    return String.join("", new String[] {
        dto.firstName.toLowerCase(),
        "@", 
        dto.lastName.toLowerCase(),
        dto.id.toString(),
        ".com"
    });
  }
  private String getRandomPhone() {
    Double number=Double.valueOf("1000000000")+ Math.random()*Double.valueOf("9000000000");
    String numberAsString=String.format("%.0f",number);
    return String.format("(%s%s%s)%s%s%s-%s%s%s%s", (Object[])numberAsString.split(""));
  }
  private DOB getDob(String[] ageGroup) {
    String[] ageRange=  ageGroup;
    if(ageRange==null) ageRange=((String) this.props.getOrDefault("patient_age_range", PATIENT_AGE_RANGE)).split("-");
    Integer lower = new Integer(ageRange[0]);
    Integer upper= new Integer(ageRange[1]);
    Integer age = this.rnd.nextInt(upper+1) + lower;
    LocalDate date = LocalDate.now().minusYears(age);
    return new DOB(date.format(DateTimeFormatter.ofPattern(PatientFactory.DATE_FORMAT)), age);
  }
  private String getGovId(Integer id) {
    Integer firstFive=this.rnd.nextInt(100000);
    return String.format("%s%04d", firstFive.toString(), id);
  }
  private String getGroupNumber() {
    String randomLetter = RandomStringUtils.randomAlphabetic(1);
    Integer random5Digits=this.rnd.nextInt(100000);
    return String.format("%s%05d", randomLetter.toUpperCase(),random5Digits);
  }
  private String getMrn() {
    Integer random6=this.rnd.nextInt(1000000);
    return String.format("%06d", random6);
  }
  private String getPostalCode() {
    Integer code=this.rnd.nextInt(100000);
    return String.format("%05d", code);
  }
  private String getStreetAddress() {
    String randomStreetName=getRandom(getProp("street_names"));
    Integer randomStreetNumber = this.rnd.nextInt(100000);
    return String.format("%d %s", randomStreetNumber, randomStreetName);
  }
  private String getInsuredName(PatientDTO dto) {
    return String.format("%s %s %s", dto.firstName, dto.middleName, dto.lastName);
  }
  private static Properties getProperties(String path) throws Exception {
    Properties props = new Properties();
    props.load(
        new FileInputStream(path)
        );
    return props;
  }
  public PatientDTO genRandomPatient(Integer nextId, String[] ageGroup) throws Exception {
    PatientDTO dto = new PatientDTO();
    dto.id = nextId;
    if(dto.id==null) dto.id=patientDAO.findNextOrdinal(Patient.class, "id");
    Integer gender = getRandom(GENDERS);
    dto.genderId=gender;
    String firstNamesProperty="first_names_female";
    if(gender.equals(MALE)) {
      firstNamesProperty="first_names_male";
    }
    dto.firstName=getRandom(getProp(firstNamesProperty));
    dto.lastName=getRandom(getProp("last_names"));
    dto.middleName=TESTER;
    dto.email=getEmail(dto);
    dto.primaryPhone=getRandomPhone();
    dto.city=getRandom(getProp("cities"));
    dto.usState=Integer.valueOf(getRandom(getProp("us_state_ids")));
    DOB dob=getDob(ageGroup);
    dto.dob=dob.dob;
    dto.patientAge=dob.age;
    dto.govtId=getGovId(dto.id);
    dto.groupNumber=getGroupNumber();
    dto.insuranceCarrier=getRandom(getProp("insurance_carriers"));
    dto.mrn=getMrn();
    dto.memberNumber=dto.mrn;
    dto.postalCode=getPostalCode();
    dto.address1=getStreetAddress();
    dto.insuredName=getInsuredName(dto);
    dto.createClinicianId = PATIENT_CLINICIAN_ID;
    return dto;
  }
  public Integer guardianNextId() throws Exception {
    return patientDAO.findNextOrdinal(Guardian.class, "id");
  }

  public Boolean patientNeedsGuardian(PatientDTO dto) {
    return dto.patientAge <= MAX_MINOR_AGE;
  }
  
  public static void main(String[] args) throws Exception {
    PropertyConfigurator.configure("web/WEB-INF/properties/log4j-seed.properties");

    WebXml webXml=WebXml.instance();
    String practice= webXml.getParam("practice");

    AntTask antTask=AntTask.instance();
    antTask.setProperty("practiceNumber", practice);
    antTask.runTask("genSeedAppContextXml");
    antTask.runTask("genSeedDataFile");


    ConfigurableApplicationContext ac = new FileSystemXmlApplicationContext(
        "seed/seed-spring-servlet.xml"
    );
        
    //Set Encryption Key
    Properties appProps= getProperties("web/WEB-INF/properties/app.properties");
    String encryptionKey=appProps.getProperty("app.encryption_key");
    DataEncryptor.setEncryptionKey(encryptionKey);

    //Setup Recovery/Activation Expirations
    RecoveryCode.RECOVERY_EXPIRATION_WINDOW = Duration.ofSeconds(new Integer(webXml.getParam("recoveryCodeExpiration")));
    RecoveryCode.ACTIVATION_EXPIRATION_WINDOW = Duration.ofSeconds(new Integer(webXml.getParam("activationCodeExpiration")));

    //Setup FilePaths
    Properties specialtyProps = getProperties(Paths.get("web/WEB-INF/properties/practice", practice,practice+"_app.properties").toFile().getAbsolutePath());
    Core.filesHome=appProps.getProperty("app.files_home");
    Core.patientDirPath=specialtyProps.getProperty("app.patient_dir_path");
    Core.appBaseDir=new File("web").getAbsolutePath();
    Core.headshotPlaceholder=appProps.getProperty("app.headshot_placeholder");

    String seedDataFile = args[1]; 
    Properties props = new Properties();
    props.load(new FileInputStream(seedDataFile));
    
    PatientDAO patientDAO= (PatientDAO) ac.getBean("patientDAO");
    PatientSeed patientSeed = new PatientSeed(patientDAO, props);
    AppDAO appDAO = (AppDAO) ac.getBean("appDAO");
    User user = appDAO.findById(User.class, PATIENT_CLINICIAN_ID);
    if(user==null) {
      ac.close();
      throw new Exception("Clinician with ID 2 does not exist");
    }

    PatientFactory patientFactory=new PatientFactory(patientDAO);
    GuardianSeed guardianSeed = new GuardianSeed(appDAO, patientDAO);

    Integer numPatients = new Integer(args[0]);
    if (numPatients>MAX_PATIENTS) {
      numPatients=MAX_PATIENTS; 
    }
    
    PatientDTO dto;
    Integer numGuardians=0;
    PatientDTO guardianDTO = new PatientDTO();
    Integer patientsCreated=0;
    for(Integer i=0;patientsCreated<numPatients;i++) {
      dto=patientSeed.genRandomPatient(null, null); 
      dto.patient = patientFactory.createNewPatient(dto);
      if (dto.patient!=null) {
        if(patientSeed.patientNeedsGuardian(dto)) {
          guardianDTO.guardian=null;  
          while(guardianDTO.guardian==null) {  
            guardianDTO=patientSeed.genRandomPatient(patientSeed.guardianNextId(), GUARDIAN_AGE_RANGE);
            guardianDTO.patient=dto.patient;
            guardianSeed.addGuardian(guardianDTO);
            if(guardianDTO.guardian != null) numGuardians+=1;
          }
        }
        patientsCreated+=1;
      }  
    }
    System.out.println(
        String.format("Created %d patients %d guardians.", patientsCreated, numGuardians)
    );
    ac.close();
  }
}
