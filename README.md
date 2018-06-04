# ClassFinder
ClassFinder is a console application that implement class finding functionality 
in a similar way to the Intellij IDEA Ctrl+N search.

## Usage
```bash
~$ java -jar classfinder.jar [FLAG] <filename> <pattern>
```
Find names of Java classes in file <filename> using search pattern <pattern>.
Search pattern <pattern> must include class name camelcase upper case letters
in the right order and it may contain lower case letters
to narrow down the search results.  
#### Example
**Input**
```bash
~$ java -jar classfinder.jar ./classes.txt FB
```
**Output**
```bash
a.b.FooBarBaz  
c.d.FooBar  
```
#### Flags  
 
**--help**  
display this help and exit

## Search rules  
Search pattern `<pattern>` must include class name camelcase upper case letters
in the right order and it may contain lower case letters to narrow down the search results,
for example `'FB'`, `'FoBa'` and `'FBar'` searches must all match
`a.b.FooBarBaz` and `c.d.FooBar` classes.

Upper case letters written in the wrong order will not find any results, for example
`'BF'` will not find `c.d.FooBar`.

If the search pattern consists of only lower case characters then the search becomes
case insensitive (`'fbb'` finds `FooBarBaz` but `'fBb'` will not).

If the search pattern ends with a space `' '` then the last word in the pattern must
also be the last word of the found class name (`'FBar '` finds `FooBar` but not `FooBarBaz`).

The search pattern may include wildcard characters `'*'` which match missing letters
(`'B*rBaz'` finds `FooBarBaz`i but `BrBaz` does not).

The found class names are sorted in alphabetical order ignoring package names
(package names are still included in the output).

