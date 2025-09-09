<h1><b><i><center><br> ROADMAP-ERP PROJECT </br></center></i></b></h1>

<center>Automation Repository for RoadMap ERP Site</center>
 
<h2><u>Prerequisites:</u></h2>

Ensure you have Eclipse installed (any version) with JDK version greater than 20, Maven and latest Chrome.
 
<h2><u>About framework:</u></h2>

For Roadmap ERP's Automation we used our **Galaxy Automation Framework - EzyAutomate** using Java. We have used leading open source tools like Selenium, RestAssured, Extent Report and TestNG.
 
<h2><u>Clone Project and Pull Latest Changes</u></h2>

- Navigate to the directory where you want to keep the project:
 
```
cd `<path-to-your-directory>`
```
 
- Clone the repository:
 
```
git clone https://github.com/`<your-username>`/`<your-repo.git>`
```

- Move into the project directory:

```
cd `<your-repo>`
```
 
 - Pull the latest changes from the main branch:
 
```
git pull origin main
```
 
<h2><u>Import into Eclipse as a Maven Project</u></h2>
 
- Open Eclipse IDE.

- Go to: File > Import...

- In the dialog, select: "*Maven > Existing Maven Projects*" 

- Select project *roadmap* and then click **Next**.

- Browse to the folder where you cloned the project - */path/to/your-repo*.

- Select the project and click **Finish**.

- Eclipse will automatically download the Maven dependencies from *pom.xml*

<h2><u>Run Maven Build in Eclipse</u></h2>

- Right-click on the project *roadmap* in Project Explorer.

- Choose Run As > Maven Build...

- In the Goals field, enter the required Maven goal (for example):
(*make sure including user and data base credentials & MailTo, MailCC, and MailBCC same as ('-DUsername=your username') in the goals*)
(*if use multiple users credentials, please include those credentials following*)

```
clean install test -DsuiteXmlFiles=TestSuites/`<your-suite-name>` -DplatformName=Chrome -Durl=`<site-url>`
```

**Note**:
*To execute the browser in Headless mode use "-DplatformName=headless" instead of chrome*

- Click Run.

<h2><u>If Want to Push Changes to Git Repository</u></h2>
  
- Check the current branch (*make sure you are on main or create a new branch*):
 
```
git checkout main
```
 
- Add your changes:
 
```
git add .
```
 
- Commit your changes with a message:

```
git commit -m "Your commit message here"
```
 
- Push your changes to GitHub:
 
``` 
git push origin main
```
 
 