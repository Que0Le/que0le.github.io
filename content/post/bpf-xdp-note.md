+++
title = "Note on working with BPF XDP"
date = "30/03/2023"
author = "me"
description = ""
+++

---
## vscode
Add `#define _GNU_SOURCE` to top of source file to fix almost all errors.

Sample `c_cpp_properties.json` configuration:
```json
{
    "configurations": [
        {
            "name": "Linux",
            "includePath": [
                "${workspaceFolder}/**",
                "/usr/include/x86_64-linux-gnu/bits/**"
            ],
            "defines": [
                "_POSIX_C_SOURCE=199309L"
            ],
            "intelliSenseMode": "linux-clang-x64",
            "compilerArgs": [
                "-O3 -Wall -Wextra -std=gnu99"
            ],
            "cStandard": "gnu99",
            "configurationProvider": "ms-vscode.makefile-tools"
        }
    ],
    "version": 4
}
```
Fix undefine clock: add to ``c_cpp_properties.json``
```bash
"defines": [
    "_POSIX_C_SOURCE=199309L"
],
```