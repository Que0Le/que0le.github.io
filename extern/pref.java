import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class CsvPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public static final String PREF_KEY = "COLE_CODEDICT_DB_FILEPATHS";
    private MultiLineStringFieldEditor multiLineEditor;

    public CsvPreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Paste CSV file paths (one per line)");
    }

    @Override
    public void createFieldEditors() {
        Composite parent = getFieldEditorParent();
        multiLineEditor = new MultiLineStringFieldEditor(PREF_KEY, "CSV File Paths:", parent);
        addField(multiLineEditor);
    }

    @Override
    public void init(IWorkbench workbench) {
        // nothing special
    }

    /**
     * Returns the list of CSV file paths as List<String>.
     */
    public static List<String> getSelectedCsvFilePaths() {
        String value = Activator.getDefault().getPreferenceStore().getString(PREF_KEY);
        return Arrays.stream(value.split("\\r?\\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Custom StringFieldEditor supporting multi-line text input.
     */
    private static class MultiLineStringFieldEditor extends StringFieldEditor {
        private Text textControl;

        public MultiLineStringFieldEditor(String name, String labelText, Composite parent) {
            super(name, labelText, parent);
        }

        @Override
        protected void doFillIntoGrid(Composite parent, int numColumns) {
            getLabelControl(parent);
            textControl = getTextControl(parent);
            GridData gd = new GridData(GridData.FILL_BOTH);
            gd.heightHint = 100; // make it taller
            gd.widthHint = 400;
            textControl.setLayoutData(gd);
        }

        @Override
        protected Text getTextControl(Composite parent) {
            if (textControl == null) {
                textControl = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
                textControl.setFont(parent.getFont());
            }
            return textControl;
        }
    }
}
