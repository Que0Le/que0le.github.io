---
title: "Latex code"
date: 2022-11-29T03:06:55+01:00
# draft: false
---

# Some useful snippets
### Figure
```tex
\begin{figure}[H]
	\centering
	\includegraphics[width=0.8\textwidth]{resources/images/Most_active_5_16_employers.PNG}
	\caption{Most active employers contributed to the 5.16 Linux kernel\cite{5_16_dev_stat}.}\label{fig:introduction:most_active_employers}
\end{figure}
% 
\begin{sidewaysfigure}
  \centering
  \includegraphics[width=1.0\textwidth]{resources/images/Network_data_flow_through_kernel.png}
  \caption{Network data flow through kernel \cite{Network_data_flow_through_kernel}.}
\end{sidewaysfigure}

```

### Label
```tex
\subsection{Background}\label{sec:introduction:background:background}
% 
\Cref{sec:reqs}
```

### List
```tex
Each example application consisted of the following components:
\begin{itemize}
   \item 
      Kernel Part: contains the in-kernel logic. 
      We implement our interceptor and processing unit here. 
   \item    
      User-space part: this is where the rest of the application are implemented. 
      In our application, this part will receive data from the kernel part for further processing. 
   \item 
      Communication: includes transmitting data and messages between the components.
   \item 
      Log: we have logging mechanism embedded in each of our program parts to keep records during the run-time of the application.
\end{itemize}

```

### Citing
```tex
@inproceedings{koopman_embedded_design_issues_1996,
	title = {Embedded system design issues (the rest of the story)},
	doi = {10.1109/ICCD.1996.563572},
	booktitle = {Proceedings {International} {Conference} on {Computer} {Design}. {VLSI} in {Computers} and {Processors}},
	author = {Koopman, P.},
	month = oct,
	year = {1996},
	note = {ISSN: 1063-6404},
	keywords = {Application software, Computer interfaces, Control systems, Embedded system, Heat engines, Mission critical systems, Signal processing, Software safety, Space heating, Vibrations},
	pages = {310--317},
}
\cite{koopman_embedded_design_issues_1996}
% 
\DeclareAcronym{af_xdp}{short={\textit{AF\_XDP}}, long={\textit{AF\_XDP}}, long-post={\acroiffirstT{\footnote{\url{https://www.kernel.org/doc/html/latest/networking/af_xdp.html}}}}, first-style = long, tag=exclude}
\ac{af_xdp}

```

### Text format
```tex
\begin{displayquote}
Oh
\end{displayquote}

% Italic
\textit{Tada}
% bold
\textbf{Hi}
% 
We divide these requirements based on their purposes: \textbf{Functional requirements} are used to assess \textit{if the approach \underline{can improve the performance} over Linux}, and \textbf{Business requirements} decide \textit{if it is worth considering \underline{whether the approach would be suitable for production}, based on factors such as cost or legal}.
```

### Page format
```tex
\clearpage
```