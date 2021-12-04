$(document).ready(function() {
    $('.mydatatable').DataTable({
        dom: 'Bfrtip',
        scrollY: 1000,
        scrollX: true,
        autoWidth: false,
        scrollCollapse: true,
        paging: true
    });
});