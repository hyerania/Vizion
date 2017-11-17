
def runPrompt(promptString, maxChoice):
    validInput = False
    while True:
        a = raw_input(promptString)
        for num in range(1, maxChoice+1):
            print num
        if a.isdigit() and (int(a) in range(1, maxChoice+1)):
            return a
        else:
            print "Please choose a valid number"


def loadTests(tests):
    print "Choose a test script:"
    for i in range(len(tests)):
        print " - ", i+1
    return

def __main__():
    tests = [1, 2, 3]
    loadTests(tests)
    runPrompt("Which test script? [1,"+len(tests).__str__()+"]: ", len(tests))
    return

__main__()