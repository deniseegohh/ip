Used GitHub Copilot 

To imrpove quality of

Parser.java code
1. Improved index validation: Ensure all commands that use indices (mark, unmark, delete, update) check for valid ranges and throw meaningful exceptions.
2. Extract logic for index parsing and validation into a separate method to reduce code duplication. (parseAndValidateIndex method)