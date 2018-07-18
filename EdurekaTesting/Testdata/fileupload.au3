ControlFocus("Open","","Edit1")
ControlSetText("Open","","Edit1", @WorkingDir & "\Testdata\" & $CmdLine[1])
ControlClick("Open","","Button1")