package controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.Buku;
import model.Pelanggan;
import model.Penjualan;
import service.BukuService;
import service.PelangganService;
import service.PenjualanService;

public class PelangganController {

    // ===== TAB: CARI BUKU =====
    @FXML private TextField txtCariBuku;
    @FXML private Button btnCariBuku;
    @FXML private Button btnRefreshCari;
    @FXML private TableView<Buku> tblCariBuku;
    @FXML private TableColumn<Buku, Integer> colCariBukuId;
    @FXML private TableColumn<Buku, String> colCariJudul;
    @FXML private TableColumn<Buku, String> colCariPenulis;
    @FXML private TableColumn<Buku, Integer> colCariStok;
    @FXML private Label lblTotalCari;

    // ===== TAB: INPUT DATA BUKU =====
    @FXML private TextField txtJudul;
    @FXML private TextField txtPenulis;
    @FXML private TextField txtHarga;
    @FXML private TextField txtStok;
    @FXML private Button btnAddBuku;
    @FXML private Button btnEditBuku;
    @FXML private Button btnDeleteBuku;
    @FXML private Button btnClearBuku;
    @FXML private TableView<Buku> tblBuku;
    @FXML private TableColumn<Buku, Integer> colBukuId;
    @FXML private TableColumn<Buku, String> colJudulTbl;
    @FXML private TableColumn<Buku, String> colPenulisTbl;
    @FXML private TableColumn<Buku, BigDecimal> colHargaTbl;
    @FXML private TableColumn<Buku, Integer> colStokTbl;
    @FXML private Label lblTotalBuku;

    // ===== TAB: INPUT DATA PELANGGAN =====
    @FXML private TextField txtNama;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelepon;
    @FXML private Button btnAddPelanggan;
    @FXML private Button btnEditPelanggan;
    @FXML private Button btnDeletePelanggan;
    @FXML private Button btnClearPelanggan;
    @FXML private TableView<Pelanggan> tblPelanggan;
    @FXML private TableColumn<Pelanggan, Integer> colPelangganId;
    @FXML private TableColumn<Pelanggan, String> colNama;
    @FXML private TableColumn<Pelanggan, String> colEmail;
    @FXML private TableColumn<Pelanggan, String> colTelepon;
    @FXML private Label lblTotalPelanggan;

    // ===== TAB: FORM PENJUALAN =====
    @FXML private ComboBox<Pelanggan> cbPelanggan;
    @FXML private ComboBox<Buku> cbBuku;
    @FXML private TextField txtJumlah;
    @FXML private TextField txtTotalHarga;
    @FXML private DatePicker dateBeli;
    @FXML private Button btnAddPenjualan;
    @FXML private Button btnDeletePenjualan;
    @FXML private Button btnClearPenjualan;
    @FXML private TableView<Penjualan> tblPenjualan;
    @FXML private TableColumn<Penjualan, Integer> colPenjualanId;
    @FXML private TableColumn<Penjualan, Integer> colJumlahPenjualan;
    @FXML private TableColumn<Penjualan, BigDecimal> colTotalPenjualan;
    @FXML private TableColumn<Penjualan, Date> colTanggalPenjualan;
    @FXML private TableColumn<Penjualan, Integer> colPelangganIdPenjualan;
    @FXML private TableColumn<Penjualan, Integer> colBukuIdPenjualan;
    @FXML private Label lblTotalPenjualan;

    // services
    private final BukuService bukuService = new BukuService();
    private final PelangganService pelangganService = new PelangganService();
    private final PenjualanService penjualanService = new PenjualanService();

    // observable lists
    private final ObservableList<Buku> bukuList = FXCollections.observableArrayList();
    private final ObservableList<Pelanggan> pelangganList = FXCollections.observableArrayList();
    private final ObservableList<Penjualan> penjualanList = FXCollections.observableArrayList();

    // helper: harga buku terpilih
    private BigDecimal hargaBukuTerpilih = BigDecimal.ZERO;

    @FXML
    public void initialize() {
        // --- init columns : CARI BUKU ---
        colCariBukuId.setCellValueFactory(new PropertyValueFactory<>("bukuId"));
        colCariJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colCariPenulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        colCariStok.setCellValueFactory(new PropertyValueFactory<>("stok"));

        // --- init columns : BUKU ---
        colBukuId.setCellValueFactory(new PropertyValueFactory<>("bukuId"));
        colJudulTbl.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colPenulisTbl.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        colHargaTbl.setCellValueFactory(new PropertyValueFactory<>("harga"));
        colStokTbl.setCellValueFactory(new PropertyValueFactory<>("stok"));

        // --- init columns : PELANGGAN ---
        colPelangganId.setCellValueFactory(new PropertyValueFactory<>("pelangganId"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelepon.setCellValueFactory(new PropertyValueFactory<>("telepon"));

        // --- init columns : PENJUALAN ---
        colPenjualanId.setCellValueFactory(new PropertyValueFactory<>("penjualanId"));
        colJumlahPenjualan.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        colTotalPenjualan.setCellValueFactory(new PropertyValueFactory<>("totalHarga"));
        colTanggalPenjualan.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colPelangganIdPenjualan.setCellValueFactory(new PropertyValueFactory<>("pelangganId"));
        colBukuIdPenjualan.setCellValueFactory(new PropertyValueFactory<>("bukuId"));

        // set table items
        tblBuku.setItems(bukuList);
        tblCariBuku.setItems(bukuList);
        tblPelanggan.setItems(pelangganList);
        tblPenjualan.setItems(penjualanList);

        // load initial data
        loadAllBuku();
        loadAllPelanggan();
        loadAllPenjualan();

        // populate comboboxes
        cbBuku.setItems(bukuList);
        cbPelanggan.setItems(pelangganList);

        // show meaningful text in ComboBox (judul / nama)
        cbBuku.setCellFactory(c -> new ListCell<>() {
            @Override
            protected void updateItem(Buku item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getJudul());
            }
        });
        cbBuku.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Buku item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getJudul());
            }
        });

        cbPelanggan.setCellFactory(c -> new ListCell<>() {
            @Override
            protected void updateItem(Pelanggan item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNama());
            }
        });
        cbPelanggan.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pelanggan item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNama());
            }
        });

        // --- setup text formatters and quick validation ---
        setupTextFormatters();

        // listeners: compute total when buku selected or jumlah changed
        cbBuku.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                hargaBukuTerpilih = newV.getHarga() != null ? newV.getHarga() : BigDecimal.ZERO;
            } else {
                hargaBukuTerpilih = BigDecimal.ZERO;
            }
            hitungTotalPenjualan();
        });

        txtJumlah.textProperty().addListener((obs, oldV, newV) -> hitungTotalPenjualan());

        // table selection fills forms
        tblBuku.getSelectionModel().selectedItemProperty().addListener((obs, oldV, buku) -> {
            if (buku != null) {
                txtJudul.setText(buku.getJudul());
                txtPenulis.setText(buku.getPenulis());
                txtHarga.setText(buku.getHarga() != null ? buku.getHarga().toPlainString() : "");
                txtStok.setText(String.valueOf(buku.getStok()));
            }
        });

        tblPelanggan.getSelectionModel().selectedItemProperty().addListener((obs, oldV, p) -> {
            if (p != null) {
                txtNama.setText(p.getNama());
                txtEmail.setText(p.getEmail());
                txtTelepon.setText(p.getTelepon());
            }
        });

        // search button (Cari Buku)
        btnCariBuku.setOnAction(e -> onSearch());
        btnRefreshCari.setOnAction(e -> onRefreshCari());

        // book CRUD handlers
        btnAddBuku.setOnAction(e -> onAddBuku());
        btnEditBuku.setOnAction(e -> onEditBuku());
        btnDeleteBuku.setOnAction(e -> onDeleteBuku());
        btnClearBuku.setOnAction(e -> clearBukuForm());

        // pelanggan CRUD handlers
        btnAddPelanggan.setOnAction(e -> onAddPelanggan());
        btnEditPelanggan.setOnAction(e -> onEditPelanggan());
        btnDeletePelanggan.setOnAction(e -> onDeletePelanggan());
        btnClearPelanggan.setOnAction(e -> clearPelangganForm());

        // penjualan handlers
        btnAddPenjualan.setOnAction(e -> onAddPenjualan());
        btnDeletePenjualan.setOnAction(e -> onDeletePenjualan());
        btnClearPenjualan.setOnAction(e -> clearPenjualanForm());
    }

    // -------------------------
    // TextFormatters & quick input helpers
    // -------------------------
    private void setupTextFormatters() {
        // txtHarga: allow digits and a single dot (decimal)
        Pattern decimalPattern = Pattern.compile("\\d*(\\.\\d*)?");
        UnaryOperator<TextFormatter.Change> filterDecimal = change -> {
            String newText = change.getControlNewText();
            return decimalPattern.matcher(newText).matches() ? change : null;
        };
        TextFormatter<String> tfHarga = new TextFormatter<>(filterDecimal);
        txtHarga.setTextFormatter(tfHarga);

        // txtStok and txtJumlah: integers only
        Pattern intPattern = Pattern.compile("\\d*");
        UnaryOperator<TextFormatter.Change> filterInt = change -> {
            String newText = change.getControlNewText();
            return intPattern.matcher(newText).matches() ? change : null;
        };
        txtStok.setTextFormatter(new TextFormatter<>(filterInt));
        txtJumlah.setTextFormatter(new TextFormatter<>(filterInt));

        // dateBeli default to today
        dateBeli.setConverter(new StringConverter<LocalDate>() {
            @Override public String toString(LocalDate object) {
                return object == null ? "" : object.toString();
            }
            @Override public LocalDate fromString(String string) {
                return (string == null || string.isBlank()) ? null : LocalDate.parse(string);
            }
        });
    }

    // -------------------------
    // Loaders
    // -------------------------
    private void loadAllBuku() {
        try {
            List<Buku> list = bukuService.getAll();
            bukuList.setAll(list);
            lblTotalBuku.setText("Total Data: " + bukuList.size());
            lblTotalCari.setText("Total Data: " + bukuList.size());
        } catch (Exception ex) {
            showError("Load Buku Error", ex.getMessage());
        }
    }

    private void loadAllPelanggan() {
        try {
            List<Pelanggan> list = pelangganService.getAll();
            pelangganList.setAll(list);
            lblTotalPelanggan.setText("Total Data: " + pelangganList.size());
        } catch (Exception ex) {
            showError("Load Pelanggan Error", ex.getMessage());
        }
    }

    private void loadAllPenjualan() {
        try {
            List<Penjualan> list = penjualanService.getAll();
            penjualanList.setAll(list);
            lblTotalPenjualan.setText("Total Data: " + penjualanList.size());
        } catch (Exception ex) {
            showError("Load Penjualan Error", ex.getMessage());
        }
    }

    // -------------------------
    // SEARCH (Cari Buku)
    // -------------------------
    private void onSearch() {
        String q = txtCariBuku.getText() == null ? "" : txtCariBuku.getText().trim();
        try {
            if (q.isEmpty()) {
                loadAllBuku();
            } else {
                // use service search if available; fallback to client-side filter
                List<Buku> results;
                try {
                    results = bukuService.searchByTitleOrAuthor(q);
                } catch (Exception ex) {
                    results = bukuService.getAll();
                }
                results.removeIf(b -> !(containsIgnoreCase(b.getJudul(), q) || containsIgnoreCase(b.getPenulis(), q)));
                bukuList.setAll(results);
                lblTotalCari.setText("Total Data: " + results.size());
            }
        } catch (Exception ex) {
            showError("Search Error", ex.getMessage());
        }
    }

    @FXML
    private void onRefreshCari() {
        loadAllBuku();
        txtCariBuku.clear();
    }

    private boolean containsIgnoreCase(String src, String q) {
        if (src == null) {
			return false;
		}
        return src.toLowerCase().contains(q.toLowerCase());
    }

    // -------------------------
    // Validation helpers
    // -------------------------
    private boolean isValidNameOrAuthor(String s) {
        if (s == null) {
			return false;
		}
        String trimmed = s.trim();
        if (trimmed.length() < 2) {
			return false;
		}
        // simbol-simbol
        return trimmed.matches("^[A-Za-zÀ-ÖØ-öø-ÿ'\\- ]{2,}$");
    }

    private boolean isValidEmail(String s) {
        if (s == null) {
			return true; // treat empty as optional; change to false if you want required
		}
        String t = s.trim();
        if (t.isEmpty()) {
			return true;
		}
        return t.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private boolean isValidPhone(String s) {
        if (s == null) {
			return true; // optional
		}
        String t = s.trim();
        if (t.isEmpty()) {
			return true;
		}
        return t.matches("^\\+?\\d{6,15}$");
    }

    private boolean isPositiveBigDecimal(String s) {
        try {
            BigDecimal bd = new BigDecimal(s.trim());
            return bd.compareTo(BigDecimal.ZERO) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNonNegativeInteger(String s) {
        try {
            if (s == null || s.trim().isEmpty()) {
				return false;
			}
            int v = Integer.parseInt(s.trim());
            return v >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    // -------------------------
    // BUKU: CRUD
    // -------------------------
    private void onAddBuku() {
        try {
            String judul = txtJudul.getText().trim();
            String penulis = txtPenulis.getText().trim();
            String hargaStr = txtHarga.getText().trim();
            String stokStr = txtStok.getText().trim();

            if (judul.isEmpty()) { showError("Validasi", "Judul tidak boleh kosong"); return; }
            if (penulis.isEmpty()) { showError("Validasi", "Penulis tidak boleh kosong"); return; }
            if (!isValidNameOrAuthor(penulis)) { showError("Validasi", "Penulis hanya boleh huruf, spasi, '-' atau '\\'' dan minimal 2 karakter"); return; }
            if (!isPositiveBigDecimal(hargaStr)) { showError("Validasi", "Harga harus angka desimal > 0 (contoh 99000.00)"); return; }
            if (!isNonNegativeInteger(stokStr)) { showError("Validasi", "Stok harus bilangan bulat >= 0"); return; }

            BigDecimal harga = new BigDecimal(hargaStr);
            int stok = Integer.parseInt(stokStr);

            Buku b = new Buku();
            b.setJudul(judul);
            b.setPenulis(penulis);
            b.setHarga(harga);
            b.setStok(stok);

            bukuService.insert(b);
            showInfo("Sukses", "Buku berhasil ditambahkan");
            clearBukuForm();
            loadAllBuku();
        } catch (Exception ex) {
            showError("Tambah Buku Error", ex.getMessage());
        }
    }

    private void onEditBuku() {
        Buku sel = tblBuku.getSelectionModel().getSelectedItem();
        if (sel == null) { showError("Pilih Data", "Pilih buku yang ingin diubah"); return; }
        try {
            String judul = txtJudul.getText().trim();
            String penulis = txtPenulis.getText().trim();
            String hargaStr = txtHarga.getText().trim();
            String stokStr = txtStok.getText().trim();

            if (judul.isEmpty()) { showError("Validasi", "Judul tidak boleh kosong"); return; }
            if (penulis.isEmpty()) { showError("Validasi", "Penulis tidak boleh kosong"); return; }
            if (!isValidNameOrAuthor(penulis)) { showError("Validasi", "Penulis hanya boleh huruf, spasi, '-' atau '\\'' dan minimal 2 karakter"); return; }
            if (!isPositiveBigDecimal(hargaStr)) { showError("Validasi", "Harga harus angka desimal > 0 (contoh 99000.00)"); return; }
            if (!isNonNegativeInteger(stokStr)) { showError("Validasi", "Stok harus bilangan bulat >= 0"); return; }

            sel.setJudul(judul);
            sel.setPenulis(penulis);
            sel.setHarga(new BigDecimal(hargaStr));
            sel.setStok(Integer.parseInt(stokStr));

            bukuService.update(sel);
            showInfo("Sukses", "Buku berhasil diupdate");
            clearBukuForm();
            loadAllBuku();
        } catch (Exception ex) {
            showError("Update Buku Error", ex.getMessage());
        }
    }

    private void onDeleteBuku() {
        Buku sel = tblBuku.getSelectionModel().getSelectedItem();
        if (sel == null) { showError("Pilih Data", "Pilih buku yang ingin dihapus"); return; }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Hapus buku: " + sel.getJudul() + " ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> opt = a.showAndWait();
        if (opt.isPresent() && opt.get() == ButtonType.YES) {
            try {
                bukuService.delete(sel.getBukuId());
                showInfo("Sukses", "Buku berhasil dihapus");
                loadAllBuku();
            } catch (Exception ex) {
                showError("Hapus Buku Error", ex.getMessage());
            }
        }
    }

    private void clearBukuForm() {
        txtJudul.clear();
        txtPenulis.clear();
        txtHarga.clear();
        txtStok.clear();
        tblBuku.getSelectionModel().clearSelection();
    }

    // -------------------------
    // PELANGGAN: CRUD
    // -------------------------
    private void onAddPelanggan() {
        try {
            String nama = txtNama.getText().trim();
            String email = txtEmail.getText().trim();
            String telepon = txtTelepon.getText().trim();

            if (nama.isEmpty()) { showError("Validasi", "Nama tidak boleh kosong"); return; }
            if (!isValidNameOrAuthor(nama)) { showError("Validasi", "Nama tidak boleh mengandung angka atau simbol selain - ' dan spasi"); return; }
            if (!isValidEmail(email)) { showError("Validasi", "Format email tidak valid"); return; }
            if (!isValidPhone(telepon)) { showError("Validasi", "Nomor telepon tidak valid (hanya angka, 6-15 digit, boleh + di depan)"); return; }

            Pelanggan p = new Pelanggan();
            p.setNama(nama);
            p.setEmail(email);
            p.setTelepon(telepon);

            pelangganService.insert(p);
            showInfo("Sukses", "Pelanggan berhasil ditambahkan");
            clearPelangganForm();
            loadAllPelanggan();
        } catch (Exception ex) {
            showError("Tambah Pelanggan Error", ex.getMessage());
        }
    }

    private void onEditPelanggan() {
        Pelanggan sel = tblPelanggan.getSelectionModel().getSelectedItem();
        if (sel == null) { showError("Pilih Data", "Pilih pelanggan yang ingin diubah"); return; }
        try {
            String nama = txtNama.getText().trim();
            String email = txtEmail.getText().trim();
            String telepon = txtTelepon.getText().trim();

            if (nama.isEmpty()) { showError("Validasi", "Nama tidak boleh kosong"); return; }
            if (!isValidNameOrAuthor(nama)) { showError("Validasi", "Nama tidak boleh mengandung angka atau simbol selain - ' dan spasi"); return; }
            if (!isValidEmail(email)) { showError("Validasi", "Format email tidak valid"); return; }
            if (!isValidPhone(telepon)) { showError("Validasi", "Nomor telepon tidak valid (hanya angka, 6-15 digit, boleh + di depan)"); return; }

            sel.setNama(nama);
            sel.setEmail(email);
            sel.setTelepon(telepon);

            pelangganService.update(sel);
            showInfo("Sukses", "Pelanggan berhasil diupdate");
            clearPelangganForm();
            loadAllPelanggan();
        } catch (Exception ex) {
            showError("Update Pelanggan Error", ex.getMessage());
        }
    }

    private void onDeletePelanggan() {
        Pelanggan sel = tblPelanggan.getSelectionModel().getSelectedItem();
        if (sel == null) { showError("Pilih Data", "Pilih pelanggan yang ingin dihapus"); return; }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Hapus pelanggan: " + sel.getNama() + " ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> opt = a.showAndWait();
        if (opt.isPresent() && opt.get() == ButtonType.YES) {
            try {
                pelangganService.delete(sel.getPelangganId());
                showInfo("Sukses", "Pelanggan berhasil dihapus");
                loadAllPelanggan();
            } catch (Exception ex) {
                showError("Hapus Pelanggan Error", ex.getMessage());
            }
        }
    }

    private void clearPelangganForm() {
        txtNama.clear();
        txtEmail.clear();
        txtTelepon.clear();
        tblPelanggan.getSelectionModel().clearSelection();
    }

    // -------------------------
    // PENJUALAN
    // -------------------------
    private void hitungTotalPenjualan() {
        try {
            int jumlah = txtJumlah.getText() == null || txtJumlah.getText().isBlank() ? 0 : Integer.parseInt(txtJumlah.getText().trim());
            BigDecimal total = hargaBukuTerpilih.multiply(BigDecimal.valueOf(jumlah));
            txtTotalHarga.setText(total.toPlainString());
        } catch (NumberFormatException ex) {
            txtTotalHarga.setText("0");
        }
    }

    private void onAddPenjualan() {
        try {
            Buku buku = cbBuku.getSelectionModel().getSelectedItem();
            if (buku == null) { showError("Validasi", "Pilih buku terlebih dahulu"); return; }

            String jumlahStr = txtJumlah.getText().trim();
            if (!isNonNegativeInteger(jumlahStr)) { showError("Validasi", "Jumlah harus bilangan bulat positif"); return; }
            int jumlah = Integer.parseInt(jumlahStr);
            if (jumlah <= 0) { showError("Validasi", "Jumlah harus lebih dari 0"); return; }

            // cek stok
            if (buku.getStok() < jumlah) {
                showError("Validasi", "Stok tidak cukup. Stok saat ini: " + buku.getStok());
                return;
            }

            LocalDate ld = dateBeli.getValue();
            if (ld == null) { showError("Validasi", "Pilih tanggal pembelian"); return; }

            Penjualan p = new Penjualan();
            p.setJumlah(jumlah);
            BigDecimal total = buku.getHarga().multiply(BigDecimal.valueOf(jumlah));
            p.setTotalHarga(total);
            p.setTanggal(Date.valueOf(ld));
            Pelanggan pel = cbPelanggan.getSelectionModel().getSelectedItem();
            p.setPelangganId(pel == null ? null : pel.getPelangganId());
            p.setBukuId(buku.getBukuId());

            penjualanService.insert(p); // service handles stok & transaction
            showInfo("Sukses", "Penjualan tercatat");
            clearPenjualanForm();
            loadAllPenjualan();
            loadAllBuku(); // update stok on buku table
        } catch (NumberFormatException ex) {
            showError("Input Error", "Jumlah harus berupa angka bulat");
        } catch (Exception ex) {
            showError("Tambah Penjualan Error", ex.getMessage());
        }
    }

    private void onDeletePenjualan() {
        Penjualan sel = tblPenjualan.getSelectionModel().getSelectedItem();
        if (sel == null) { showError("Pilih Data", "Pilih penjualan yang ingin dihapus"); return; }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Hapus penjualan ID: " + sel.getPenjualanId() + " ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> opt = a.showAndWait();
        if (opt.isPresent() && opt.get() == ButtonType.YES) {
            try {
                penjualanService.delete(sel.getPenjualanId());
                showInfo("Sukses", "Penjualan berhasil dihapus");
                loadAllPenjualan();
                loadAllBuku();
            } catch (Exception ex) {
                showError("Hapus Penjualan Error", ex.getMessage());
            }
        }
    }

    private void clearPenjualanForm() {
        cbBuku.getSelectionModel().clearSelection();
        cbPelanggan.getSelectionModel().clearSelection();
        txtJumlah.clear();
        txtTotalHarga.clear();
        dateBeli.setValue(null);
        tblPenjualan.getSelectionModel().clearSelection();
    }

    // -------------------------
    // Helpers: Alerts
    // -------------------------
    private void showError(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(title);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void showInfo(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(title);
        a.setContentText(msg);
        a.showAndWait();
    }
}
