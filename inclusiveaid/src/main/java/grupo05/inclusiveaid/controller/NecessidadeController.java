@RestController
@RequestMapping("/necessidade")
public class NecessidadeController {

    @Autowired
    private NecessidadeRepository necessidadeRepository;

    @GetMapping
    public List<Necessidade> listar() {
        return necessidadeRepository.findAll();
    }

    @PostMapping
    public Necessidade salvar(@RequestBody Necessidade necessidade) {
        return necessidadeRepository.save(necessidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        necessidadeRepository.deleteById(id);
    }
}
