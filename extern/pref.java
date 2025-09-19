import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.ScrolledComposite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;

public class CsvPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private DirectoryFieldEditor directoryEditor;
    private Composite checkboxContainer;
    private ScrolledComposite scrolledComposite;
    private List<Button> checkboxButtons = new ArrayList<>();

    public static final String PREF_KEY = "COLE_CODEDICT_DB_FILEPATHS";

    public CsvPreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Select CSV files for COLE_CODEDICT_DB_FILEPATHS");
    }

    @Override
    public void createFieldEditors() {
        Composite parent = getFieldEditorParent();

        directoryEditor = new DirectoryFieldEditor("csvDirectory", "Target Folder:", parent);
        addField(directoryEditor);

        Button scanButton = new Button(parent, SWT.PUSH);
        scanButton.setText("Scan for CSV Files");
        scanButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                scanAndPopulate();
            }
        });

        scrolledComposite = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER);
        scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);

        checkboxContainer = new Composite(scrolledComposite, SWT.NONE);
        checkboxContainer.setLayout(new GridLayout(1, false));
        scrolledComposite.setContent(checkboxContainer);
    }

    private void scanAndPopulate() {
        // Clear old checkboxes
        for (Button b : checkboxButtons) {
            b.dispose();
        }
        checkboxButtons.clear();

        String folderPath = directoryEditor.getStringValue();
        if (folderPath == null || folderPath.isEmpty()) {
            return;
        }

        List<File> csvFiles = new ArrayList<>();
        collectCsvFiles(new File(folderPath), csvFiles);

        for (File file : csvFiles) {
            Button checkbox = new Button(checkboxContainer, SWT.CHECK);
            checkbox.setText(file.getAbsolutePath());
            checkboxButtons.add(checkbox);
        }

        checkboxContainer.layout(true);
        scrolledComposite.setMinSize(checkboxContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    }

    private void collectCsvFiles(File folder, List<File> result) {
        if (folder == null || !folder.exists()) return;
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File f : files) {
            if (f.isDirectory()) {
                collectCsvFiles(f, result);
            } else if (f.getName().toLowerCase().endsWith(".csv")) {
                result.add(f);
            }
        }
    }

    @Override
    public boolean performOk() {
        // Collect selected file paths
        List<String> selectedPaths = new ArrayList<>();
        for (Button b : checkboxButtons) {
            if (b.getSelection()) {
                selectedPaths.add(b.getText());
            }
        }

        String joined = String.join(";", selectedPaths);
        getPreferenceStore().setValue(PREF_KEY, joined);

        return super.performOk();
    }

    @Override
    public void init(IWorkbench workbench) {
        // Nothing special
    }
}
