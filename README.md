
# EOX Test Automation Utilities as Dependecy and its usage

This repo is specifically for publishing the different Utilities that have been written for Test Automation. The Following readme file describes about what it contains and how to use this Dependecy in your Test Automation framework.

---


## **Table of Contents**
1. [Features](#features)
2. [Installation](#installation)
3. [Usage](#usage)

---

## **Features**
- **Driver Manager:** Handles WebDriver initialization and teardown.
- **Wait Utilities:** Provides explicit and fluent wait functionalities.
- **Data Readers:** Support for reading from Excel, CSV, and property files.
- **Config Manager:** Loads and manages configuration properties.
- **Custom Assertions:** Reusable methods for test validations.
- **Report Generation:** Hooks for generating reports (like Allure or ExtentReports).

---

## **Installation**
To use this utility package in your automation framework, follow these steps:

1. Go to this [link](https://github.com/harshakreox/EOXTestAutomationUtilities/packages/2282992)

2. Copy the latest dependecy displayed which should look something like this,
```
<dependency>
  <groupId>EOXTestAutomationUtilities</groupId>
  <artifactId>eoxtestautomationutilities</artifactId>
  <version>${version}</version>
</dependency>
```

3. Paste the copied dependecy in `pom.xml` file present in your automation framework.

4. Once done, save the file and wait for the resources to get downloaded.
5.Now open your terminal, navigate to your root directory where the `pom.xml` is present and run the following command `mvn clean install`.

6. Now you are all set !

## **Usage**

## Authors

- [@harshakreox](https://www.github.com/harshakreox)
