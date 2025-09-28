# Document Processor Engine

## Introduction

This project is a sophisticated document processing engine built in Java. It is designed to read plain text or annotated files, apply a user-defined set of rules to format the content, and then export the result into various formats such as Markdown or PDF. The engine is highly customizable, allowing users to define their own rulesets to handle different types of documents and formatting requirements.

## Features

- **Rule-Based Processing**: The core of the engine is a powerful rule-based system that allows for flexible and extensible document processing.
- **Multiple Input Formats**: Supports both raw plain text and annotated (e.g., HTML) files as input.
- **Multiple Export Formats**: Processed documents can be exported to Markdown and PDF.
- **Customizable Rulesets**: Users can define their own rulesets to specify how documents should be processed and formatted. Rules can be based on various criteria, such as text starting with a certain prefix, all-caps text, or specific line positions.
- **Statistics Reporting**: The engine provides a report with statistics about the processed document, including the total number of paragraphs and words.

## Architecture

The project is organized into the following main components:

- **`client`**: Contains the user interface components (e.g., `UI.java`).
- **`dataload`**: Handles the loading of raw data from files (e.g., `RawFileLineLoader.java`).
- **`datamodel`**: Defines the data structures used in the application, including building blocks like `Document` and `LineBlock`, as well as the rule-related classes (`AbstractRule`, `RuleSet`, etc.).
- **`engine`**: The core of the application, where the rules are applied to the data model (`Engine.java`).
- **`exporters`**: Contains the logic for exporting the processed documents into different formats (e.g., `MarkdownExporter.java`, `PdfExporter.java`).
- **`test`**: Includes test classes for the project, providing examples of how to use the engine (`TestEngine.java`).

## Usage

The following examples, derived from the project's test suite, demonstrate how to use the engine to process a document.

### Example: Processing a Plain Text File

This example shows how to process a plain text file (`hippocratesOath.txt`) and apply a custom ruleset to format the content.

```java
// 1. Initialize the engine with the input file and a profile name
String inputFileName = "Resources/SampleDocs/hippocratesOath.txt";
IPlainTextDocumentEngine engine = new Engine(inputFileName, "RAW", "happyhippo");

// 2. Define the ruleset
List<List<String>> inputSpec = new ArrayList<List<String>>();

List<String> h1List = new ArrayList<String>();
h1List.add("H1");
h1List.add("STARTS_WITH");
h1List.add("OATH AND");
inputSpec.add(h1List);

List<String> omList = new ArrayList<String>();
omList.add("OMIT");
omList.add("POSITIONS");
omList.add("0,3");
inputSpec.add(omList);

List<String> h2List = new ArrayList<String>();
h2List.add("H2");
h2List.add("ALL_CAPS");
inputSpec.add(h2List);

List<String> italicsList = new ArrayList<String>();
italicsList.add("<I>");
italicsList.add("POSITIONS");
italicsList.add("4,16");
inputSpec.add(italicsList);

// 3. Register the ruleset with the engine
engine.registerInputRuleSetForPlainFiles(inputSpec);

// 4. Load the file and characterize the blocks
int inputBlocks = engine.loadFileAndCharacterizeBlocks();

// 5. Export the processed document to Markdown
String outputFileName = "Resources//Outputs//hippocratesOath.txt.md";
int outputParagraphs = engine.exportMarkDown(outputFileName);

// 6. Get a report with statistics
List<String> report = engine.reportWithStats();
```

### Example: Processing an Annotated HTML File

This example demonstrates how to process an HTML file (`hippocratesOath.html`) by defining rules that recognize HTML tags.

```java
// 1. Initialize the engine for an annotated file
String inputFileName = "Resources/SampleDocs/hippocratesOath.html";
IPlainTextDocumentEngine engine = new Engine(inputFileName, "ANNOTATED", "happyhippoHTML");

// 2. Define the ruleset based on HTML tags
List<List<String>> inputSpec = new ArrayList<List<String>>();
List<String> h1List = new ArrayList<String>();
h1List.add("H1");
h1List.add("STARTS_WITH");
h1List.add("<H1>");
inputSpec.add(h1List);

List<String> h2List = new ArrayList<String>();
h2List.add("H2");
h2List.add("STARTS_WITH");
h2List.add("<H2>");
inputSpec.add(h2List);

List<String> italicsList = new ArrayList<String>();
italicsList.add("<I>");
italicsList.add("STARTS_WITH");
italicsList.add("<i>");
inputSpec.add(italicsList);

List<String> boldList = new ArrayList<String>();
boldList.add("<B>");
boldList.add("STARTS_WITH");
boldList.add("<b>");
inputSpec.add(boldList);

// 3. Register the ruleset and tag prefixes
List<String> prefixes = new ArrayList<String>();
prefixes.add("<H1>");
prefixes.add("<H2>");
prefixes.add("<i>");
prefixes.add("<b>");
prefixes.add("<p>");
engine.registerInputRuleSetForAnnotatedFiles(inputSpec, prefixes);

// 4. Export the processed document to Markdown
String outputFileName = "Resources//Outputs//hippocratesOathHtml.md";
int outputParagraphs = engine.exportMarkDown(outputFileName);
```

## Building and Running

To build and run the project, you will need to have Java and a Java IDE (such as Eclipse or IntelliJ IDEA) installed. The project also uses JUnit for testing.

### Dependencies

- **JUnit**: For running the test suite.
- **Apache Commons IO**: Used for file handling in the tests.

Make sure to include these dependencies in your project's classpath.

### Running the Tests

To run the tests, you can execute the `TestEngine.java` class as a JUnit test case in your IDE. This will run the test methods and process the sample documents as shown in the usage examples.

## Contributing

Contributions to this project are welcome. Please fork the repository and submit a pull request with your changes. Before submitting, ensure that all tests pass and that your code adheres to the existing coding style.