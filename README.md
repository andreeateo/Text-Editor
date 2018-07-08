# Text-Editor

A text-editor application with the following features:
- Spelling correction
- AutoComplete option
- Markov Text generator
- Readability score calculation
- Word Path generator

**Project Structure**

- *src*: project source code
- *deps_src*: source file dependencies
- *lib*: library dependencies
- *test*: **JUnit** tests
- *test_cases*: test data files
- *.project*: Eclipse project file
- *.classpath*

**Prerequisites**

Eclipse IDE - for download and install go to: https://www.eclipse.org/

**Set-up**

1. Download the project on your drive.
2. Create a new Java Project in your Eclipse workspace.
3. Import the files: File, Import, Select "File System", Next, Browse and choose the root directory where you downloaded the files, Finish.

**Testing**

There are JUnit test files in the test folder.

**Using the Text Editor Application**

1. Run the project.
2. In the GUI, type, load text by using the "Load text" button or use copy/paste.
3. By selecting the "Spelling suggestions" option the app will highlight spelling mistakes and the "AutoComplete" option will provide 6 suggestions for the word you are typing.
4. Calculate readability score of your text using the "Flesch Index" button.
5. The "Edit distance" button calculates the word path between two words:
	*cake -> caked -> coked -> cowed -> crowed -> crowd 
	Number of steps: 5*
6. The "Generate Markov Text" function works by generating new text based on the text from step 2 keeping the original sequence of neighboring words.
7. The "Clear" button clears the text area.

**Author**

Andreea Teodor

**Credits**

This application was written as part of an assignment for: *Data Structures and Performance – University of California San Diego on Coursera*
(the source files in the *deps_src* folder and the GUI Application were provided as a starter code).
